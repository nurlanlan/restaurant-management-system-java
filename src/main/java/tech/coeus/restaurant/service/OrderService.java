package tech.coeus.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.coeus.restaurant.dto.request.OrderItemRequest;
import tech.coeus.restaurant.dto.request.OrderRequest;
import tech.coeus.restaurant.dto.response.OrderItemResponse;
import tech.coeus.restaurant.dto.response.OrderResponse;
import tech.coeus.restaurant.exception.ResourceNotFoundException;
import tech.coeus.restaurant.model.*;
import tech.coeus.restaurant.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Find the restaurant table
        RestaurantTable table = tableRepository.findById(orderRequest.getTableId())
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + orderRequest.getTableId()));

        Long waiterId = orderRequest.getWaiterId();
        User waiter;

        if (waiterId == null) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            waiter = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Current user not found"));
        } else {
            waiter = userRepository.findById(waiterId)
                    .orElseThrow(() -> new ResourceNotFoundException("Waiter not found with id: " + waiterId));
        }

        Order order = new Order();
        order.setTable(table);
        order.setWaiter(waiter);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(orderRequest.getStatus());
        order.setTotalPrice(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order); 

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + itemRequest.getMenuItemId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItems.add(orderItem);

            BigDecimal itemPrice = menuItem.getPrice().multiply(itemRequest.getQuantity());
            totalPrice = totalPrice.add(itemPrice);
        }

        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);

        savedOrder.setTotalPrice(totalPrice);
        savedOrder = orderRepository.save(savedOrder);

        if (table.getStatus() == RestaurantTable.TableStatus.AVAILABLE) {
            table.setStatus(RestaurantTable.TableStatus.OCCUPIED);
            tableRepository.save(table);
        }

        List<OrderItemResponse> orderItemResponses = savedOrderItems.stream()
                .map(item -> new OrderItemResponse(
                        item.getId(),
                        item.getMenuItem().getId(),
                        item.getMenuItem().getName(),
                        item.getMenuItem().getPrice(),
                        item.getQuantity(),
                        item.getMenuItem().getPrice().multiply(item.getQuantity())
                )).collect(Collectors.toList());

        return new OrderResponse(
                savedOrder.getId(),
                table.getId(),
                table.getTableNumber(),
                waiter.getId(),
                waiter.getFullName(),
                savedOrder.getOrderTime(),
                savedOrder.getStatus(),
                savedOrder.getTotalPrice(),
                orderItemResponses
        );
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapOrderToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        return mapOrderToOrderResponse(order);
    }

    public List<OrderResponse> getOrdersByTable(Long tableId) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + tableId));

        return orderRepository.findByTable(table).stream()
                .map(this::mapOrderToOrderResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersByWaiter(Long waiterId) {
        User waiter = userRepository.findById(waiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Waiter not found with id: " + waiterId));

        return orderRepository.findByWaiter(waiter).stream()
                .map(this::mapOrderToOrderResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(this::mapOrderToOrderResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        if (status == Order.OrderStatus.PAID) {
            RestaurantTable table = order.getTable();
            table.setStatus(RestaurantTable.TableStatus.CLEANING);
            tableRepository.save(table);
        }

        return mapOrderToOrderResponse(updatedOrder);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        orderItemRepository.deleteAll(orderItemRepository.findByOrder(order));

        orderRepository.delete(order);
    }


    private OrderResponse mapOrderToOrderResponse(Order order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder(order);

        List<OrderItemResponse> orderItemResponses = orderItems.stream()
                .map(item -> new OrderItemResponse(
                        item.getId(),
                        item.getMenuItem().getId(),
                        item.getMenuItem().getName(),
                        item.getMenuItem().getPrice(),
                        item.getQuantity(),
                        item.getMenuItem().getPrice().multiply(item.getQuantity())
                )).collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getTable().getId(),
                order.getTable().getTableNumber(),
                order.getWaiter().getId(),
                order.getWaiter().getFullName(),
                order.getOrderTime(),
                order.getStatus(),
                order.getTotalPrice(),
                orderItemResponses
        );
    }
}
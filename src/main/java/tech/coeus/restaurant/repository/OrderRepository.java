package tech.coeus.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.coeus.restaurant.model.Order;
import tech.coeus.restaurant.model.RestaurantTable;
import tech.coeus.restaurant.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByTable(RestaurantTable table);

    List<Order> findByTableAndStatus(RestaurantTable table, Order.OrderStatus status);

    List<Order> findByWaiter(User waiter);

    List<Order> findByStatus(Order.OrderStatus status);

    List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
}
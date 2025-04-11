package tech.coeus.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.coeus.restaurant.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long tableId;
    private Integer tableNumber;
    private Long waiterId;
    private String waiterName;
    private LocalDateTime orderTime;
    private Order.OrderStatus status;
    private BigDecimal totalPrice;
    private List<OrderItemResponse> items;
}
package tech.coeus.restaurant.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.coeus.restaurant.model.Order;

import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private Long tableId;

    private Long waiterId;

    @NotNull
    private Order.OrderStatus status;

    @NotEmpty
    private List<OrderItemRequest> orderItems;
}
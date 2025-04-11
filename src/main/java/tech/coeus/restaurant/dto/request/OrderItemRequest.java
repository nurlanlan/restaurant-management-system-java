package tech.coeus.restaurant.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {
    @NotNull
    private Long menuItemId;

    @NotNull
    @Min(1)
    private BigDecimal quantity;
}
package tech.coeus.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemResponse {
    private Long id;
    private Long menuItemId;
    private String menuItemName;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal subtotal;
}

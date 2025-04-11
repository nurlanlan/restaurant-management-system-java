package tech.coeus.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.coeus.restaurant.model.MenuItem;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MenuItemResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean available;
    private MenuItem.MenuCategory category;
}
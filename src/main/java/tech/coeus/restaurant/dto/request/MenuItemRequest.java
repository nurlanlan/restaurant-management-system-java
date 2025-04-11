package tech.coeus.restaurant.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tech.coeus.restaurant.model.MenuItem;

import java.math.BigDecimal;

@Data
public class MenuItemRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal price;

    private Boolean available = true;

    @NotNull
    private MenuItem.MenuCategory category;
}

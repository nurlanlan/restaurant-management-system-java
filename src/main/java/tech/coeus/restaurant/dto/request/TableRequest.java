package tech.coeus.restaurant.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.coeus.restaurant.model.RestaurantTable;

@Data
public class TableRequest {
    @NotNull
    @Min(1)
    private Integer tableNumber;

    @NotNull
    private RestaurantTable.TableStatus status;

    @NotNull
    private RestaurantTable.TableSection section;
}
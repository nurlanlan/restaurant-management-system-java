package tech.coeus.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.coeus.restaurant.model.RestaurantTable;

@Data
@AllArgsConstructor
public class TableResponse {
    private Long id;
    private Integer tableNumber;
    private RestaurantTable.TableStatus status;
    private RestaurantTable.TableSection section;
}
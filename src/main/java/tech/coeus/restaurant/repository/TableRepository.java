package tech.coeus.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.coeus.restaurant.model.RestaurantTable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    Optional<RestaurantTable> findByTableNumber(Integer tableNumber);

    List<RestaurantTable> findByStatus(RestaurantTable.TableStatus status);

    List<RestaurantTable> findBySection(RestaurantTable.TableSection section);
}
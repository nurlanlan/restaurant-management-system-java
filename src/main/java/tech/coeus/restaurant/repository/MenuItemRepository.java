package tech.coeus.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.coeus.restaurant.model.MenuItem;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(MenuItem.MenuCategory category);

    List<MenuItem> findByAvailableTrue();

    List<MenuItem> findByAvailableTrueAndCategory(MenuItem.MenuCategory category);
}
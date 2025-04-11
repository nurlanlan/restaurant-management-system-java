package tech.coeus.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.coeus.restaurant.dto.request.MenuItemRequest;
import tech.coeus.restaurant.dto.response.MenuItemResponse;
import tech.coeus.restaurant.exception.ResourceNotFoundException;
import tech.coeus.restaurant.model.MenuItem;
import tech.coeus.restaurant.repository.MenuItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setAvailable(menuItemRequest.getAvailable());
        menuItem.setCategory(menuItemRequest.getCategory());

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return new MenuItemResponse(
                savedMenuItem.getId(),
                savedMenuItem.getName(),
                savedMenuItem.getPrice(),
                savedMenuItem.getAvailable(),
                savedMenuItem.getCategory()
        );
    }

    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItem -> new MenuItemResponse(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getAvailable(),
                        menuItem.getCategory()
                )).collect(Collectors.toList());
    }

    public MenuItemResponse getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));

        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.getAvailable(),
                menuItem.getCategory()
        );
    }

    public List<MenuItemResponse> getMenuItemsByCategory(MenuItem.MenuCategory category) {
        return menuItemRepository.findByCategory(category).stream()
                .map(menuItem -> new MenuItemResponse(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getAvailable(),
                        menuItem.getCategory()
                )).collect(Collectors.toList());
    }

    public List<MenuItemResponse> getAvailableMenuItems() {
        return menuItemRepository.findByAvailableTrue().stream()
                .map(menuItem -> new MenuItemResponse(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getAvailable(),
                        menuItem.getCategory()
                )).collect(Collectors.toList());
    }

    public List<MenuItemResponse> getAvailableMenuItemsByCategory(MenuItem.MenuCategory category) {
        return menuItemRepository.findByAvailableTrueAndCategory(category).stream()
                .map(menuItem -> new MenuItemResponse(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getAvailable(),
                        menuItem.getCategory()
                )).collect(Collectors.toList());
    }

    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest menuItemRequest) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));

        menuItem.setName(menuItemRequest.getName());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setAvailable(menuItemRequest.getAvailable());
        menuItem.setCategory(menuItemRequest.getCategory());

        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);

        return new MenuItemResponse(
                updatedMenuItem.getId(),
                updatedMenuItem.getName(),
                updatedMenuItem.getPrice(),
                updatedMenuItem.getAvailable(),
                updatedMenuItem.getCategory()
        );
    }

    public MenuItemResponse updateMenuItemAvailability(Long id, boolean available) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));

        menuItem.setAvailable(available);
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);

        return new MenuItemResponse(
                updatedMenuItem.getId(),
                updatedMenuItem.getName(),
                updatedMenuItem.getPrice(),
                updatedMenuItem.getAvailable(),
                updatedMenuItem.getCategory()
        );
    }

    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Menu item not found with id: " + id);
        }
        menuItemRepository.deleteById(id);
    }
}
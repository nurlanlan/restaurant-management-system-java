package tech.coeus.restaurant.controller;

import tech.coeus.restaurant.dto.request.TableRequest;
import tech.coeus.restaurant.dto.response.TableResponse;
import tech.coeus.restaurant.model.RestaurantTable;
import tech.coeus.restaurant.service.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<TableResponse> createTable(@Valid @RequestBody TableRequest tableRequest) {
        return ResponseEntity.ok(tableService.createTable(tableRequest));
    }

    @GetMapping
    public ResponseEntity<List<TableResponse>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableResponse> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @GetMapping("/number/{tableNumber}")
    public ResponseEntity<TableResponse> getTableByNumber(@PathVariable Integer tableNumber) {
        return ResponseEntity.ok(tableService.getTableByNumber(tableNumber));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TableResponse>> getTablesByStatus(@PathVariable RestaurantTable.TableStatus status) {
        return ResponseEntity.ok(tableService.getTablesByStatus(status));
    }

    @GetMapping("/section/{section}")
    public ResponseEntity<List<TableResponse>> getTablesBySection(@PathVariable RestaurantTable.TableSection section) {
        return ResponseEntity.ok(tableService.getTablesBySection(section));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<TableResponse> updateTable(@PathVariable Long id, @Valid @RequestBody TableRequest tableRequest) {
        return ResponseEntity.ok(tableService.updateTable(id, tableRequest));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'WAITER')")
    public ResponseEntity<TableResponse> updateTableStatus(
            @PathVariable Long id,
            @RequestParam RestaurantTable.TableStatus status) {
        return ResponseEntity.ok(tableService.updateTableStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
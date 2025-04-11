package tech.coeus.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.coeus.restaurant.dto.request.TableRequest;
import tech.coeus.restaurant.dto.response.TableResponse;
import tech.coeus.restaurant.exception.ResourceNotFoundException;
import tech.coeus.restaurant.model.RestaurantTable;
import tech.coeus.restaurant.repository.TableRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public TableResponse createTable(TableRequest tableRequest) {
        // Check if table number exists
        if (tableRepository.findByTableNumber(tableRequest.getTableNumber()).isPresent()) {
            throw new RuntimeException("Table number already exists!");
        }

        RestaurantTable table = new RestaurantTable();
        table.setTableNumber(tableRequest.getTableNumber());
        table.setStatus(tableRequest.getStatus());
        table.setSection(tableRequest.getSection());

        RestaurantTable savedTable = tableRepository.save(table);

        return new TableResponse(
                savedTable.getId(),
                savedTable.getTableNumber(),
                savedTable.getStatus(),
                savedTable.getSection()
        );
    }

    public List<TableResponse> getAllTables() {
        return tableRepository.findAll().stream()
                .map(table -> new TableResponse(
                        table.getId(),
                        table.getTableNumber(),
                        table.getStatus(),
                        table.getSection()
                )).collect(Collectors.toList());
    }

    public TableResponse getTableById(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));

        return new TableResponse(
                table.getId(),
                table.getTableNumber(),
                table.getStatus(),
                table.getSection()
        );
    }

    public TableResponse getTableByNumber(Integer tableNumber) {
        RestaurantTable table = tableRepository.findByTableNumber(tableNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with number: " + tableNumber));

        return new TableResponse(
                table.getId(),
                table.getTableNumber(),
                table.getStatus(),
                table.getSection()
        );
    }

    public List<TableResponse> getTablesByStatus(RestaurantTable.TableStatus status) {
        return tableRepository.findByStatus(status).stream()
                .map(table -> new TableResponse(
                        table.getId(),
                        table.getTableNumber(),
                        table.getStatus(),
                        table.getSection()
                )).collect(Collectors.toList());
    }

    public List<TableResponse> getTablesBySection(RestaurantTable.TableSection section) {
        return tableRepository.findBySection(section).stream()
                .map(table -> new TableResponse(
                        table.getId(),
                        table.getTableNumber(),
                        table.getStatus(),
                        table.getSection()
                )).collect(Collectors.toList());
    }

    public TableResponse updateTable(Long id, TableRequest tableRequest) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));

        if (!table.getTableNumber().equals(tableRequest.getTableNumber()) &&
                tableRepository.findByTableNumber(tableRequest.getTableNumber()).isPresent()) {
            throw new RuntimeException("Table number already exists!");
        }

        table.setTableNumber(tableRequest.getTableNumber());
        table.setStatus(tableRequest.getStatus());
        table.setSection(tableRequest.getSection());

        RestaurantTable updatedTable = tableRepository.save(table);

        return new TableResponse(
                updatedTable.getId(),
                updatedTable.getTableNumber(),
                updatedTable.getStatus(),
                updatedTable.getSection()
        );
    }

    public TableResponse updateTableStatus(Long id, RestaurantTable.TableStatus status) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));

        table.setStatus(status);
        RestaurantTable updatedTable = tableRepository.save(table);

        return new TableResponse(
                updatedTable.getId(),
                updatedTable.getTableNumber(),
                updatedTable.getStatus(),
                updatedTable.getSection()
        );
    }

    public void deleteTable(Long id) {
        if (!tableRepository.existsById(id)) {
            throw new ResourceNotFoundException("Table not found with id: " + id);
        }
        tableRepository.deleteById(id);
    }
}
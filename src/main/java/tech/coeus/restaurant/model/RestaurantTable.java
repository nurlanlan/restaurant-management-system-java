package tech.coeus.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number", nullable = false, unique = true)
    private Integer tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TableStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "section", nullable = false)
    private TableSection section;

    public enum TableStatus {
        AVAILABLE, OCCUPIED, RESERVED, CLEANING
    }

    public enum TableSection {
        INDOOR, OUTDOOR, BALCONY, VIP, BAR
    }
}
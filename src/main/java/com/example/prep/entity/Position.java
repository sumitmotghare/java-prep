package com.example.prep.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String symbol;           // e.g. AAPL, MSFT

    @Column(nullable = false)
    private Integer quantity;        // number of shares

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal price;        // price per share

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Side side;               // LONG or SHORT

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    public enum Side {
        LONG, SHORT
    }
}

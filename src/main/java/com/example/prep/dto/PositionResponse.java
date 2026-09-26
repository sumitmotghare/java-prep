package com.example.prep.dto;

import com.example.prep.entity.Position;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class PositionResponse {

    private Long id;
    private String symbol;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal marketValue;   // quantity × price — computed, not stored
    private Position.Side side;
    private Instant createdAt;
    private Instant updatedAt;
}

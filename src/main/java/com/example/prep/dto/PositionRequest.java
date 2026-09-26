package com.example.prep.dto;

import com.example.prep.entity.Position;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PositionRequest {

    @NotBlank(message = "Symbol is required")
    @Size(max = 20, message = "Symbol must be 20 characters or fewer")
    @Pattern(regexp = "^[A-Z]+$", message = "Symbol must be uppercase letters only")
    private String symbol;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0001", message = "Price must be greater than zero")
    @Digits(integer = 15, fraction = 4, message = "Price format invalid")
    private BigDecimal price;

    @NotNull(message = "Side is required")
    private Position.Side side;
}

package com.example.prep.mapper;

import com.example.prep.dto.PositionRequest;
import com.example.prep.dto.PositionResponse;
import com.example.prep.entity.Position;
import org.mapstruct.*;
import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    Position toEntity(PositionRequest request);

    @Mapping(target = "marketValue", expression =
        "java(computeMarketValue(position))")
    PositionResponse toResponse(Position position);

    default BigDecimal computeMarketValue(Position position) {
        return position.getPrice()
            .multiply(BigDecimal.valueOf(position.getQuantity()));
    }
}

package com.example.prep.mapper;

import com.example.prep.dto.PositionRequest;
import com.example.prep.dto.PositionResponse;
import com.example.prep.entity.Position;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-26T16:14:46+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 26.0.2.1 (Homebrew)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position toEntity(PositionRequest request) {
        if ( request == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.symbol( request.getSymbol() );
        position.quantity( request.getQuantity() );
        position.price( request.getPrice() );
        position.side( request.getSide() );

        return position.build();
    }

    @Override
    public PositionResponse toResponse(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionResponse.PositionResponseBuilder positionResponse = PositionResponse.builder();

        positionResponse.id( position.getId() );
        positionResponse.symbol( position.getSymbol() );
        positionResponse.quantity( position.getQuantity() );
        positionResponse.price( position.getPrice() );
        positionResponse.side( position.getSide() );
        positionResponse.createdAt( position.getCreatedAt() );
        positionResponse.updatedAt( position.getUpdatedAt() );

        positionResponse.marketValue( computeMarketValue(position) );

        return positionResponse.build();
    }
}

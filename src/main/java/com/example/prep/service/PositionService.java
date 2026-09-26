package com.example.prep.service;

import com.example.prep.dto.PositionRequest;
import com.example.prep.dto.PositionResponse;
import java.util.List;

public interface PositionService {
    List<PositionResponse> getAllPositions();
    PositionResponse getPositionById(Long id);
    PositionResponse createPosition(PositionRequest request);
}

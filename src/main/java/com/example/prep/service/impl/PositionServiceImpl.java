package com.example.prep.service.impl;

import com.example.prep.dto.PositionRequest;
import com.example.prep.dto.PositionResponse;
import com.example.prep.entity.Position;
import com.example.prep.exception.ResourceNotFoundException;
import com.example.prep.mapper.PositionMapper;
import com.example.prep.repository.PositionRepository;
import com.example.prep.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor    // Lombok: constructor injection — no @Autowired needed
@Slf4j
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    @Transactional(readOnly = true)  // Hint to Hibernate: no dirty checking needed
    public List<PositionResponse> getAllPositions() {
        log.info("Fetching all positions");
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponse getPositionById(Long id) {
        log.info("Fetching position with id={}", id);
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Position not found with id: " + id));
        return positionMapper.toResponse(position);
    }

    @Override
    @Transactional
    public PositionResponse createPosition(PositionRequest request) {
        log.info("Creating position for symbol={}", request.getSymbol());
        Position position = positionMapper.toEntity(request);
        Position saved = positionRepository.save(position);
        log.info("Position created with id={}", saved.getId());
        return positionMapper.toResponse(saved);
    }
}

package com.example.prep.controller;

import com.example.prep.dto.PositionRequest;
import com.example.prep.dto.PositionResponse;
import com.example.prep.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
@Tag(name = "Positions", description = "Manage trading positions")
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    @Operation(summary = "Get all positions")
    public ResponseEntity<List<PositionResponse>> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a position by ID")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.getPositionById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new position")
    public ResponseEntity<PositionResponse> createPosition(
            @Valid @RequestBody PositionRequest request) {
        PositionResponse response = positionService.createPosition(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

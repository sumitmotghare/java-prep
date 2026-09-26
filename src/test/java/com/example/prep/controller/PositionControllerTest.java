package com.example.prep.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.prep.dto.PositionRequest;
import com.example.prep.dto.PositionResponse;
import com.example.prep.entity.Position;
import com.example.prep.exception.ResourceNotFoundException;
import com.example.prep.service.PositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PositionController.class)
@AutoConfigureMockMvc(addFilters = false)
class PositionControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean  PositionService positionService;

    private PositionResponse sampleResponse() {
        return PositionResponse.builder()
                .id(1L)
                .symbol("AAPL")
                .quantity(100)
                .price(new BigDecimal("175.5000"))
                .marketValue(new BigDecimal("17550.0000"))
                .side(Position.Side.LONG)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    void getAllPositions_returnsOk() throws Exception {
        when(positionService.getAllPositions()).thenReturn(List.of(sampleResponse()));

        mockMvc.perform(get("/api/positions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$[0].marketValue").value(17550.0));
    }

    @Test
    void getPositionById_found() throws Exception {
        when(positionService.getPositionById(1L)).thenReturn(sampleResponse());

        mockMvc.perform(get("/api/positions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.symbol").value("AAPL"));
    }

    @Test
    void getPositionById_notFound_returns404() throws Exception {
        when(positionService.getPositionById(99L))
                .thenThrow(new ResourceNotFoundException("Position not found with id: 99"));

        mockMvc.perform(get("/api/positions/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Position not found with id: 99"));
    }

    @Test
    void createPosition_validRequest_returns201() throws Exception {
        PositionRequest req = new PositionRequest();
        req.setSymbol("MSFT");
        req.setQuantity(50);
        req.setPrice(new BigDecimal("320.0000"));
        req.setSide(Position.Side.LONG);

        when(positionService.createPosition(any())).thenReturn(sampleResponse());

        mockMvc.perform(post("/api/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    void createPosition_invalidSymbol_returns400() throws Exception {
        PositionRequest req = new PositionRequest();
        req.setSymbol("aapl");       // lowercase — should fail validation
        req.setQuantity(100);
        req.setPrice(new BigDecimal("175.00"));
        req.setSide(Position.Side.LONG);

        mockMvc.perform(post("/api/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.symbol").exists());
    }

    @Test
    void createPosition_missingFields_returns400() throws Exception {
        mockMvc.perform(post("/api/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields").isMap());
    }
}
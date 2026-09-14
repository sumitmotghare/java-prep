package main.java.com.example.prep.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class StatusResponse {
    private String status;
    private String service;
    private String version;
    private Instant timestamp;
}

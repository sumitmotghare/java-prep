package com.example.prep.controller;

import com.example.prep.dto.StatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;

@RestController
@RequestMapping("/api")
public class StatusController {

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getStatus() {
        return ResponseEntity.ok(
            StatusResponse.builder()
                .status("ok")
                .service("jefferies-prep")
                .version(appVersion)
                .timestamp(Instant.now())
                .build()
        );
    }
}

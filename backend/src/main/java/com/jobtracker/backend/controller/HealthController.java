package com.jobtracker.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class HealthController {

    private final DataSource dataSource;

    public HealthController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public String checkHealth() {
        try (Connection conn = dataSource.getConnection()) {
            return "✅ Backend is Alive! Database Connection is VALID: " + conn.getCatalog();
        } catch (Exception e) {
            return "❌ Database Connection Failed: " + e.getMessage();
        }
    }
}
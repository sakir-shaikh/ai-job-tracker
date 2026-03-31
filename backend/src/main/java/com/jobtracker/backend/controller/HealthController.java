package com.jobtracker.backend.controller;

import com.jobtracker.backend.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @GetMapping(AppConstants.HEALTH_PATH)
    public String healthCheck() {
        try (Connection conn = dataSource.getConnection()) {
            return String.format(AppConstants.HEALTH_SUCCESS, conn.getCatalog());
        } catch (Exception e) {
            return String.format(AppConstants.HEALTH_FAILURE, e.getMessage());
        }
    }
}

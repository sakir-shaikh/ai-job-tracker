package com.jobtracker.backend.config;

public class AppConstants {
    // Pagination Defaults
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";

    // API Paths
    public static final String API_BASE_PATH = "/api/jobs";
    public static final String ID_PATH_VARIABLE = "/{id}";
    public static final String HEALTH_PATH = "/health";

    // Exception Messages
    public static final String JOB_NOT_FOUND_MSG = "Job not found with id: %d";

    // Validation Messages
    public static final String COMPANY_MANDATORY = "Company name is mandatory";
    public static final String TITLE_MANDATORY = "Job title is mandatory";
    public static final String STATUS_MANDATORY = "Status is mandatory";
    public static final String LOCATION_MANDATORY = "Location is mandatory";

    // Health Check Messages
    public static final String HEALTH_SUCCESS = "✅ Backend is Alive! Database Connection is VALID: %s";
    public static final String HEALTH_FAILURE = "❌ Database Connection Failed: %s";
}

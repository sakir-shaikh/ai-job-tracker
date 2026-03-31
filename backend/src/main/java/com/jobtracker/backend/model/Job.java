package com.jobtracker.backend.model;

import com.jobtracker.backend.config.AppConstants;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.COMPANY_MANDATORY)
    private String company;
    @NotBlank(message = AppConstants.TITLE_MANDATORY)
    private String title;
    private String description;

    @NotNull(message = AppConstants.STATUS_MANDATORY)
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @NotNull(message = AppConstants.LOCATION_MANDATORY)
    @Enumerated(EnumType.STRING)
    private JobLocation location;

    private LocalDate dateApplied;
    private String jobLink;
}

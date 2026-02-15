package com.jobtracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Data
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name is mandatory")
    private String company;
    @NotBlank(message = "job title is mandatory")
    private String title;
    private String description;

    @NotNull(message = "status is mandatory")
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @NotNull(message = "location is mandatory")
    @Enumerated(EnumType.STRING)
    private JobLocation location;

    private LocalDate dateApplied;
    private String jobLink;
}
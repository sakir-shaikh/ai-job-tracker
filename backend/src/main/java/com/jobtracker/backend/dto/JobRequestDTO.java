package com.jobtracker.backend.dto;

import com.jobtracker.backend.model.JobLocation;
import com.jobtracker.backend.model.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDTO {

    @NotBlank(message = "Company name is mandatory")
    private String company;

    @NotBlank(message = "Job title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Status is mandatory")
    private JobStatus status;

    @NotNull(message = "Location is mandatory")
    private JobLocation location;

    private String jobLink;
}

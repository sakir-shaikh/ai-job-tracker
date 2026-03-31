package com.jobtracker.backend.dto;

import com.jobtracker.backend.model.JobLocation;
import com.jobtracker.backend.model.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDTO {
    private Long id;
    private String company;
    private String title;
    private String description;
    private JobStatus status;
    private JobLocation location;
    private LocalDate dateApplied;
    private String jobLink;
}

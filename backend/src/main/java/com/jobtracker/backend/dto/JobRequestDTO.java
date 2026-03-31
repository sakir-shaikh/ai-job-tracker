package com.jobtracker.backend.dto;

import com.jobtracker.backend.config.AppConstants;
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

    @NotBlank(message = AppConstants.COMPANY_MANDATORY)
    private String company;

    @NotBlank(message = AppConstants.TITLE_MANDATORY)
    private String title;

    private String description;

    @NotNull(message = AppConstants.STATUS_MANDATORY)
    private JobStatus status;

    @NotNull(message = AppConstants.LOCATION_MANDATORY)
    private JobLocation location;

    private String jobLink;
}

package com.jobtracker.backend.mapper;

import com.jobtracker.backend.dto.JobRequestDTO;
import com.jobtracker.backend.dto.JobResponseDTO;
import com.jobtracker.backend.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public Job toEntity(JobRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Job job = new Job();
        job.setCompany(dto.getCompany());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setStatus(dto.getStatus());
        job.setLocation(dto.getLocation());
        job.setJobLink(dto.getJobLink());
        return job;
    }

    public JobResponseDTO toResponseDTO(Job job) {
        if (job == null) {
            return null;
        }
        return JobResponseDTO.builder()
                .id(job.getId())
                .company(job.getCompany())
                .title(job.getTitle())
                .description(job.getDescription())
                .status(job.getStatus())
                .location(job.getLocation())
                .dateApplied(job.getDateApplied())
                .jobLink(job.getJobLink())
                .build();
    }

    public void updateEntityFromDTO(JobRequestDTO dto, Job job) {
        if (dto == null || job == null) {
            return;
        }
        job.setCompany(dto.getCompany());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setStatus(dto.getStatus());
        job.setLocation(dto.getLocation());
        job.setJobLink(dto.getJobLink());
    }
}

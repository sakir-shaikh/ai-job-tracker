package com.jobtracker.backend.service;

import com.jobtracker.backend.dto.JobRequestDTO;
import com.jobtracker.backend.dto.JobResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Page<JobResponseDTO> getAllJobs(String search, Pageable pageable);
    JobResponseDTO getJobById(Long id);
    JobResponseDTO createJob(JobRequestDTO jobRequestDTO);
    JobResponseDTO updateJob(Long id, JobRequestDTO jobRequestDTO);
    void deleteJob(Long id);
}

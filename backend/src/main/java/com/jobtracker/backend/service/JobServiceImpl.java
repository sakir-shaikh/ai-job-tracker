package com.jobtracker.backend.service;

import com.jobtracker.backend.config.AppConstants;
import com.jobtracker.backend.dto.JobRequestDTO;
import com.jobtracker.backend.dto.JobResponseDTO;
import com.jobtracker.backend.exception.JobNotFoundException;
import com.jobtracker.backend.mapper.JobMapper;
import com.jobtracker.backend.model.Job;
import com.jobtracker.backend.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository repository;
    private final JobMapper mapper;

    public JobServiceImpl(JobRepository repository, JobMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobResponseDTO> getAllJobs(String search, Pageable pageable) {
        if (search != null && !search.isEmpty()) {
            return repository.findByCompanyContainingIgnoreCaseOrTitleContainingIgnoreCase(search, search, pageable)
                    .map(mapper::toResponseDTO);
        }
        return repository.findAll(pageable).map(mapper::toResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public JobResponseDTO getJobById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new JobNotFoundException(String.format(AppConstants.JOB_NOT_FOUND_MSG, id)));
    }

    @Override
    public JobResponseDTO createJob(JobRequestDTO dto) {
        Job job = mapper.toEntity(dto);
        if (job.getDateApplied() == null) {
            job.setDateApplied(LocalDate.now());
        }
        return mapper.toResponseDTO(repository.save(job));
    }

    @Override
    public JobResponseDTO updateJob(Long id, JobRequestDTO dto) {
        Job existingJob = repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(String.format(AppConstants.JOB_NOT_FOUND_MSG, id)));

        mapper.updateEntityFromDTO(dto, existingJob);
        return mapper.toResponseDTO(repository.save(existingJob));
    }

    @Override
    public void deleteJob(Long id) {
        if (!repository.existsById(id)) {
            throw new JobNotFoundException(String.format(AppConstants.JOB_NOT_FOUND_MSG, id));
        }
        repository.deleteById(id);
    }
}

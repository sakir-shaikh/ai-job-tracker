package com.jobtracker.backend.service;

import com.jobtracker.backend.dto.JobRequestDTO;
import com.jobtracker.backend.dto.JobResponseDTO;
import com.jobtracker.backend.mapper.JobMapper;
import com.jobtracker.backend.model.Job;
import com.jobtracker.backend.model.JobLocation;
import com.jobtracker.backend.model.JobStatus;
import com.jobtracker.backend.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    private JobRepository repository;

    @Spy
    private JobMapper mapper = new JobMapper(); // Using real mapper or can be mocked

    @InjectMocks
    private JobServiceImpl service;

    private JobRequestDTO jobRequestDTO;
    private Job job;

    @BeforeEach
    void setUp() {
        jobRequestDTO = JobRequestDTO.builder()
                .company("Google")
                .title("Software Engineer")
                .status(JobStatus.APPLIED)
                .location(JobLocation.REMOTE)
                .build();

        job = mapper.toEntity(jobRequestDTO);
        job.setId(1L);
    }

    @Test
    void shouldCreateJobSuccessfully() {
        when(repository.save(any(Job.class))).thenReturn(job);

        JobResponseDTO created = service.createJob(jobRequestDTO);

        assertNotNull(created);
        assertEquals("Google", created.getCompany());
        verify(repository, times(1)).save(any(Job.class));
    }

    @Test
    void shouldGetAllJobsSuccessfully() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Job> jobPage = new PageImpl<>(Collections.singletonList(job));
        when(repository.findAll(pageable)).thenReturn(jobPage);

        Page<JobResponseDTO> result = service.getAllJobs(null, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Google", result.getContent().get(0).getCompany());
    }

    @Test
    void shouldSearchJobsSuccessfully() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Job> jobPage = new PageImpl<>(Collections.singletonList(job));
        String search = "Google";
        when(repository.findByCompanyContainingIgnoreCaseOrTitleContainingIgnoreCase(search, search, pageable))
                .thenReturn(jobPage);

        Page<JobResponseDTO> result = service.getAllJobs(search, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(repository).findByCompanyContainingIgnoreCaseOrTitleContainingIgnoreCase(search, search, pageable);
    }

    @Test
    void shouldGetJobByIdSuccessfully() {
        when(repository.findById(1L)).thenReturn(Optional.of(job));

        JobResponseDTO result = service.getJobById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldDeleteJobSuccessfully() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteJob(1L);

        verify(repository).deleteById(1L);
    }
}

package com.jobtracker.backend.service;

import com.jobtracker.backend.model.Job;
import com.jobtracker.backend.model.JobStatus;
import com.jobtracker.backend.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    @Mock
    private JobRepository repository; // The Fake Database

    @InjectMocks
    private JobService service; // The Real Service using the Fake DB

    @Test
    void shouldCreateJobSuccessfully() {
        // Arrange (Prepare the data)
        Job job = new Job();
        job.setCompany("Google");
        job.setStatus(JobStatus.APPLIED);

        when(repository.save(any(Job.class))).thenReturn(job); // Teach the fake DB what to do

        // Act (Run the method)
        Job created = service.createJob(job);

        // Assert (Check the result)
        assertNotNull(created);
        assertEquals("Google", created.getCompany());
        verify(repository, times(1)).save(job); // Verify save was called once
    }
}
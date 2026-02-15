package com.jobtracker.backend.service;

import com.jobtracker.backend.model.Job;
import com.jobtracker.backend.exception.JobNotFoundException;
import com.jobtracker.backend.repository.JobRepository;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Page<Job> getAllJobs(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Job createJob(Job job) {
        if (job.getDateApplied() == null) {
            job.setDateApplied(java.time.LocalDate.now());
        }
        return repository.save(job);
    }

    //update logic here
    public Job updateJob(Long id, Job updatedJob) {
        Job existingJob = repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + id));

        existingJob.setCompany(updatedJob.getCompany());
        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setStatus(updatedJob.getStatus());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setJobLink(updatedJob.getJobLink());
        existingJob.setDateApplied(updatedJob.getDateApplied());

        return repository.save(existingJob);
    }


    public void deleteJob(Long id) {
        Job job = repository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found with id: " + id)
                );

        repository.delete(job);
    }

    public Job getJobById(final Long id){
        return repository.findById(id).orElseThrow(() -> new JobNotFoundException(String.format("job with id %d not found", id)));
    }


}
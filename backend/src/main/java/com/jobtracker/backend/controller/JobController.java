package com.jobtracker.backend.controller;

import com.jobtracker.backend.dto.JobRequestDTO;
import com.jobtracker.backend.dto.JobResponseDTO;
import com.jobtracker.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createJob(jobRequestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<JobResponseDTO>> getAllJobs(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return ResponseEntity.ok(service.getAllJobs(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long id){
        return ResponseEntity.ok(service.getJobById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long id, @Valid @RequestBody JobRequestDTO jobRequestDTO) {
        return ResponseEntity.ok(service.updateJob(id, jobRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}

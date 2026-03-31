package com.jobtracker.backend.repository;

import com.jobtracker.backend.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByCompanyContainingIgnoreCaseOrTitleContainingIgnoreCase(String company, String title, Pageable pageable);
}

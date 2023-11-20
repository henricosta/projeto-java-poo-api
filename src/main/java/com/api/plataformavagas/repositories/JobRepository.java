package com.api.plataformavagas.repositories;

import com.api.plataformavagas.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Page<Job> findByTitleIgnoreCaseContainingAndType(String search, String type, Pageable pageable);

    Page<Job> findByTitleIgnoreCaseContaining(String search, Pageable pageable);

    Page<Job> findByType(String type, Pageable pageable);
}

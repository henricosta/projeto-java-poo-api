package com.api.plataformavagas.repositories;

import com.api.plataformavagas.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}

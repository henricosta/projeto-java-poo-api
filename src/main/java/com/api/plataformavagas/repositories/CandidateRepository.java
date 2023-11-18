package com.api.plataformavagas.repositories;

import com.api.plataformavagas.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}

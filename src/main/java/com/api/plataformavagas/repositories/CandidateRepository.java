package com.api.plataformavagas.repositories;

import com.api.plataformavagas.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    UserDetails findByEmail(String email);
}

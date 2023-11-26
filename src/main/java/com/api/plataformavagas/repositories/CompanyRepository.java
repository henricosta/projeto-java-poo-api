package com.api.plataformavagas.repositories;

import com.api.plataformavagas.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    UserDetails findByEmail (String email);
}

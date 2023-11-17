package com.api.plataformavagas.repositories;

import com.api.plataformavagas.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}

package com.api.plataformavagas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.plataformavagas.repositories.CandidateRepository;
import com.api.plataformavagas.repositories.CompanyRepository;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails candidateUser = candidateRepository.findByEmail(username);

        if (candidateUser != null) {
            return candidateUser;
        }

        return companyRepository.findByEmail(username);
    }
}
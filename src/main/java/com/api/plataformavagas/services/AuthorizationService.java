package com.api.plataformavagas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.plataformavagas.repositories.CandidateRepository;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return candidateRepository.findByEmail(username);
    }
}
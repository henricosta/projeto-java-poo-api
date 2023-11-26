package com.api.plataformavagas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.plataformavagas.models.Candidate;
import com.api.plataformavagas.models.Education;
import com.api.plataformavagas.repositories.EducationRepository;
import com.api.plataformavagas.requests.CreateEducationRequest;

@RestController
@RequestMapping("/education")
public class EducationController {
    @Autowired
    private EducationRepository educationRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createEducation(@RequestBody CreateEducationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        Candidate c = (Candidate) principal;

        try {
            Education e = new Education(request.title(), request.institution(), request.description(), c);

            educationRepository.save(e);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

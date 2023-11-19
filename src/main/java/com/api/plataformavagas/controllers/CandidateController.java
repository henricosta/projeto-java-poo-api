package com.api.plataformavagas.controllers;

import com.api.plataformavagas.models.Candidate;
import com.api.plataformavagas.repositories.CandidateRepository;
import com.api.plataformavagas.requests.RegisterCandidateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/details")
    public ResponseEntity<Candidate> getCandidateDetails(@RequestParam int candidate_id) {
        Candidate c = candidateRepository.findById(candidate_id).orElse(null);

        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<String> getCandidateDetails(@RequestBody RegisterCandidateRequest request) {
        try {
            Candidate c = new Candidate();
            c.setName(request.getName());
            c.setEmail(request.getEmail());
            c.setPhone(request.getPhone());
            c.setPassword(request.getPassword());

            this.candidateRepository.save(c);

            return new ResponseEntity<>("Candidate successful created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing registration request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

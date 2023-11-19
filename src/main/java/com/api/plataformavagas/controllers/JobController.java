package com.api.plataformavagas.controllers;

import com.api.plataformavagas.models.Job;
import com.api.plataformavagas.repositories.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/details")
    public ResponseEntity<Job> getJobDetails(@RequestParam int job_id) {
        Job j = this.jobRepository.findById(job_id).orElse(null);

        if (j == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(j, HttpStatus.OK);
    }

}

package com.api.plataformavagas.controllers;

import com.api.plataformavagas.models.Company;
import com.api.plataformavagas.models.Job;
import com.api.plataformavagas.repositories.CompanyRepository;
import com.api.plataformavagas.repositories.JobRepository;
import com.api.plataformavagas.requests.CreateJobRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobController(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/details")
    public ResponseEntity<Job> getJobDetails(@RequestParam int job_id) {
        Job j = this.jobRepository.findById(job_id).orElse(null);

        if (j == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(j, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Job>> listJobs(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Job> resultPage;

        try {
            if (title != null && type != null) {
                resultPage = this.jobRepository.findByTitleIgnoreCaseContainingAndType(title, type, pageable);
            } else if (title != null) {
                resultPage = this.jobRepository.findByTitleIgnoreCaseContaining(title, pageable);
            } else if (type != null) {
                resultPage = this.jobRepository.findByType(type, pageable);
            } else {
                resultPage = this.jobRepository.findAll(pageable);
            }

            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody CreateJobRequest request) {
        Company c = this.companyRepository.findById(request.getCompany_id()).orElse(null);
        if (c == null) {
            return new ResponseEntity<>("Company does not exist", HttpStatus.BAD_REQUEST);
        }

        try {
            Job j = new Job();
            j.setTitle(request.getTitle());
            j.setDescription(request.getDescription());
            j.setType(request.getType());
            j.setCompany(c);

            this.jobRepository.save(j);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

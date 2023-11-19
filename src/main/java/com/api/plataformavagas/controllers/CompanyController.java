package com.api.plataformavagas.controllers;

import com.api.plataformavagas.models.Company;
import com.api.plataformavagas.repositories.CompanyRepository;
import com.api.plataformavagas.requests.RegisterCompanyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyRepository companyRepository;


    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/details")
    public ResponseEntity<Company> getCompanyById(@RequestParam int company_id) {
        Company c = companyRepository.findById(company_id).orElse(null);

        if (c != null) {
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createCompany(@RequestBody RegisterCompanyRequest request) {
        try {
            Company c = new Company();
            c.setName(request.getName());
            c.setEmail(request.getEmail());
            c.setPhone(request.getPhone());
            c.setPassword(request.getPassword());

            this.companyRepository.save(c);

            return new ResponseEntity<>("Company successful created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing registration request", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

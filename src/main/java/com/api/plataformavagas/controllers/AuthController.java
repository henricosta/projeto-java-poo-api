package com.api.plataformavagas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.plataformavagas.models.Candidate;
import com.api.plataformavagas.models.Company;
import com.api.plataformavagas.repositories.CandidateRepository;
import com.api.plataformavagas.repositories.CompanyRepository;
import com.api.plataformavagas.requests.LoginRequest;
import com.api.plataformavagas.requests.RegisterCandidateRequest;
import com.api.plataformavagas.requests.RegisterCompanyRequest;
import com.api.plataformavagas.responses.CandidateLoginResponse;
import com.api.plataformavagas.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login/candidate")
    public ResponseEntity loginCandidate(@RequestBody LoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateCandidateToken((Candidate) auth.getPrincipal());
        return ResponseEntity.ok(new CandidateLoginResponse(token));
        
    }

    @PostMapping("/register/candidate")
    public ResponseEntity register(@RequestBody RegisterCandidateRequest request){
        if(this.candidateRepository.findByEmail(request.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

        Candidate c = new Candidate();
        c.setName(request.getName());
        c.setEmail(request.getEmail());
        c.setPhone(request.getPhone());
        c.setPassword(encryptedPassword);

        this.candidateRepository.save(c);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/company")
    public ResponseEntity register(@RequestBody RegisterCompanyRequest request){
        if(this.companyRepository.findByEmail(request.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

        
        Company c = new Company();
        c.setName(request.getName());
        c.setEmail(request.getEmail());
        c.setPhone(request.getPhone());
        c.setPassword(encryptedPassword);

        this.companyRepository.save(c);
        
        return ResponseEntity.ok().build();
    }
}
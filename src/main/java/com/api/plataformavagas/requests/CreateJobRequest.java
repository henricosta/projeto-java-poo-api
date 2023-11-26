package com.api.plataformavagas.requests;

public record CreateJobRequest(String title, String description, String type, int company_id) {
    
}

package com.onerivet.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicApi() {
        return "Public API";
    }

    @GetMapping("/user")
    public String userApi() {
        return "User API - Auth required";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    public String adminApi() {
        return "Admin API - ADMIN role required";
    }
    
    @GetMapping("/templates")
    @PreAuthorize("hasRole('Admin')")
    public String getTemplates() {
    	return "templates api";
    }
}
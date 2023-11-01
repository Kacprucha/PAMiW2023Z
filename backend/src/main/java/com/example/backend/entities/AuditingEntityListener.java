package com.example.backend.entities;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditingEntityListener implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor() {
        // Get the username from the JWT token
        String username = getCurrentUsername();

        return Optional.of (username);
    }

    private String getCurrentUsername() {
        // Access the SecurityContext to get the current username
        String result;

        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) 
            result = SecurityContextHolder.getContext().getAuthentication().getName();
        else 
            result = "anonymous"; // Set a default or anonymous user if not authenticated

        return result;
    }
}

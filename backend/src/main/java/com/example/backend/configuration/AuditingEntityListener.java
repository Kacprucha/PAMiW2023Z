package com.example.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditingEntityListener implements AuditorAware<String>
{
    @Override
    public Optional<String> getCurrentAuditor() 
    {
        // Get the username from the JWT token
        String username = getCurrentUsername();

        return Optional.of (username);
    }

    private String getCurrentUsername() 
    {
        return Optional.of(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .map(v -> v.getName())
        .orElse("anonymous");
    }
}

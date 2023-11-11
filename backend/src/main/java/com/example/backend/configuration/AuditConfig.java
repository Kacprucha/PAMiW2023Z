package com.example.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.domain.AuditorAware;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Configuration
@EnableWebSecurity
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig 
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                // Allow access to Swagger
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/h2-console/**"
                ).permitAll()
                // Authenticate all other requests
                .anyRequest().authenticated()
            )
            // Use basic authentication (user/pass)
            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(SecurityContextHolder.getContext())
        .map(v -> v.getAuthentication())
        .map(v -> v.getName());
    }

    @Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

}

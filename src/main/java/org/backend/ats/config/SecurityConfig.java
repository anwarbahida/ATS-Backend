package org.backend.ats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Désactive le CSRF pour les requêtes API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // ✅ Autorise les endpoints publics (inscription, login)
                        .anyRequest().authenticated() // 🔒 Le reste nécessite une auth
                )
                .formLogin(form -> form.disable()) // ❌ Supprime la page /login par défaut
                .httpBasic(basic -> basic.disable()); // ❌ Supprime l’auth Basic

        return http.build();
    }
}

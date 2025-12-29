package com.kbtu.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Отключаем CSRF, так как для REST API он обычно не нужен
                .csrf(csrf -> csrf.disable())

                // 2. Разрешаем POST запросы к вашему API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/users/**").permitAll() // Разрешить всем для тестов
                        .anyRequest().authenticated()
                )

                // 3. Настраиваем способ авторизации (например, Basic Auth для Postman/Curl)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

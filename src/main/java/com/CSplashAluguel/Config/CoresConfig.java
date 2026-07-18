package com.CSplashAluguel.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Libera o front-end React (localhost:5173) para chamar a API.
 * Coloque este arquivo em: src/main/java/com/CSplashAluguel/Config/
 *
 * IMPORTANTE: no SecurityConfig, adicione .cors(Customizer.withDefaults())
 * na cadeia do SecurityFilterChain, por exemplo:
 *
 *   http.cors(Customizer.withDefaults())
 *       .csrf(csrf -> csrf.disable())
 *       ...
 */
@Configuration
public class CoresConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:5173",   // Vite dev
                "http://localhost:4173"    // Vite preview
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

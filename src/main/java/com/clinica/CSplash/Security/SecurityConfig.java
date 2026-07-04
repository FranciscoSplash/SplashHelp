package com.clinica.CSplash.Security;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTTokens jwtTokens;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/auth/cadastrar", "/api/auth/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html").permitAll()


                        // AUTORIZAÇÃO DO CLIENTE
                        .requestMatchers(HttpMethod.POST, "/api/locacao").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.GET, "/api/locacao/{id}").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.DELETE, "/api/locacao/{id}").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.PUT, "/api/locacao/{id}/devolver").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.PUT, "/api/locacao/{id}/cancelar").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.POST, "/api/avaliacao").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/{id}").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.DELETE, "/api/avaliacao/{id}").hasAnyAuthority("ADMIN", "USER_Client")

                        .requestMatchers(HttpMethod.PUT, "/api/pagmento/{id}/confirmar").hasAnyAuthority("ADMIN", "USER_Client")
                        .requestMatchers(HttpMethod.POST, "/api/pagamento/gerarpagamento").hasAnyAuthority("ADMIN", "USER_Client")


                        //PERMISSÕES DO PROPRIETARIO

                        .requestMatchers(HttpMethod.PUT, "/api/locacao/{id}/retirar").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.POST, "/api/Documentacao").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.GET, "/api/Documentacao/{id}").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.DELETE, "/api/Documentacao/{id}").hasAnyAuthority("ADMIN", "USER_Prop")

                        .requestMatchers(HttpMethod.POST, "/api/carro").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.GET, "/api/carro/{id}").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.DELETE, "/api/carro/{id}").hasAnyAuthority("ADMIN", "USER_Prop")
                        .anyRequest().hasAuthority("ADMIN"))
                .addFilterBefore(jwtTokens, UsernamePasswordAuthenticationFilter.class).build();

    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
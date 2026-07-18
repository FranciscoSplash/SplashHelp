package com.CSplashAluguel.Security;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestClient;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTTokens jwtTokens;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/auth/cadastrar", "/api/auth/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/carro/proximos").permitAll()

                        // LEITURA para qualquer usuário autenticado (cliente E proprietário)
                        .requestMatchers(HttpMethod.GET, "/api/carro/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/Categoria/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/locacao/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/usuario/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/usuario/{id}").authenticated()


                        // AUTORIZAÇÃO DO CLIENTE
                        .requestMatchers(HttpMethod.POST, "/api/locacao").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.GET, "/api/locacao/{id}").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.DELETE, "/api/locacao/{id}").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.PUT, "/api/locacao/{id}/devolver").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.PUT, "/api/locacao/{id}/cancelar").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.POST, "/api/avaliacao").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.GET, "/api/avaliacao/{id}").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.DELETE, "/api/avaliacao/{id}").hasAnyAuthority("ADMIN", "User_Client")


                        .requestMatchers(HttpMethod.GET, "/api/usuario/{id}").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.PUT, "/api/pagmento/{id}/confirmar").hasAnyAuthority("ADMIN", "User_Client")
                        .requestMatchers(HttpMethod.POST, "/api/pagamento/gerarpagamento").hasAnyAuthority("ADMIN", "User_Client")


                        //PERMISSÕES DO PROPRIETARIO

                        .requestMatchers(HttpMethod.PUT, "/api/locacao/{id}/retirar").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.POST, "/api/carro").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.PUT, "/api/carro/{id}").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers(HttpMethod.DELETE, "/api/carro/{id}").hasAnyAuthority("ADMIN", "USER_Prop")
                        .requestMatchers("/api/Documentacao/**").hasAnyAuthority("ADMIN", "USER_Prop")
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
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RestClient.Builder restbuilder(){
        return RestClient.builder();
    }
}
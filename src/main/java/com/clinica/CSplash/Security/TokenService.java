package com.clinica.CSplash.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.clinica.CSplash.Model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secreta;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreta);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar Token", e);
        }
    }
    public String validarToken(String validar){

        try{
            Algorithm algorithm=Algorithm.HMAC256(secreta);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(validar)
                    .getSubject();
        }
        catch (JWTVerificationException e){
            return "";
        }
}
        private Instant getExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    }


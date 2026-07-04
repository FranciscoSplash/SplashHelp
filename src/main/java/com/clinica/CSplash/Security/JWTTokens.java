package com.clinica.CSplash.Security;

import com.clinica.CSplash.Repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTTokens extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token=this.recoverToken(request);

        if(token !=null){
            var subjet = tokenService.validarToken(token);
            if(!subjet.isEmpty()){
                UserDetails user=usuarioRepository.findByEmail(subjet);


                if (user != null){
                    var autenticacao=new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }

            }
        }
        filterChain.doFilter(request,response);
    }
private String recoverToken(HttpServletRequest request){
    var autorizicao= request.getHeader("Authorization");
    if (autorizicao ==null){
        return null;
    }
    if(autorizicao.toLowerCase().startsWith("bearer")){
       return autorizicao.substring(7).trim();
    }
    return autorizicao.trim();
}
    }

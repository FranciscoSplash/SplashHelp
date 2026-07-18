package com.CSplashAluguel.Security;

import com.CSplashAluguel.Model.Usuario;
import com.CSplashAluguel.Repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

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
                Optional<Usuario> user=usuarioRepository.findByEmail(subjet);


                if (user.isPresent()){
                    var autenticacao=new UsernamePasswordAuthenticationToken(user.get(), null,user.get().getAuthorities());
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

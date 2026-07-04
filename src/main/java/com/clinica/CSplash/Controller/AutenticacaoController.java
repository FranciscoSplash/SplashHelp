package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.CadastroRequest;
import com.clinica.CSplash.DTO.Request.LoginRequest;
import com.clinica.CSplash.DTO.Response.LoginResponse;
import com.clinica.CSplash.DTO.Response.UsuarioResponse;
import com.clinica.CSplash.Model.Usuario;
import com.clinica.CSplash.Security.TokenService;
import com.clinica.CSplash.Service.AutenticacaoService;
import com.clinica.CSplash.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest request){

        var userPassword=new UsernamePasswordAuthenticationToken(request.email(),request.senha());

        var autenticacao = authenticationManager.authenticate(userPassword);

        System.out.println("Autenticou com sucesso");

        var user = (Usuario) autenticacao.getPrincipal();

        //GERAR o token de verdade para o usuário!
        String token = tokenService.gerarToken(user);


        // Devolve os dados no AuthResponse com o status 202 Accepted
        System.out.println("Entrou no login");
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(new LoginResponse(token));
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse>Cadastrar(@RequestBody @Valid CadastroRequest request){
        return new ResponseEntity<>(usuarioService.criarUsuario(request.usuarioRequest()),HttpStatus.CREATED);
    }
}

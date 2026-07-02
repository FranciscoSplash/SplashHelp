package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.CategoriaRequest;
import com.clinica.CSplash.DTO.Request.UsuarioRequest;
import com.clinica.CSplash.DTO.Response.CategoriaResponse;
import com.clinica.CSplash.DTO.Response.UsuarioResponse;
import com.clinica.CSplash.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuario")

public class UsuarioControler {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping

    @Operation(summary = "Listar", description = "Listar todos usuarios")
    public ResponseEntity<List<UsuarioResponse>> listarUsuario(){
        return new ResponseEntity<>(usuarioService.listarUsuario(),HttpStatus.OK);
    }
    @GetMapping("/{id}")

    @Operation(summary = "Listar Id", description = "Listar Usuario por Id")
    public ResponseEntity<UsuarioResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(usuarioService.listarPorId(id), HttpStatus.OK);
    }

    @PostMapping

    @Operation(summary = "Criar", description = "Cadastrar usuario")
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        return new ResponseEntity<>(usuarioService.criarUsuario(request), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")

    @Operation(summary = "atualizar", description = "Atualizar Usuario")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioRequest request) {
        return new ResponseEntity<>(usuarioService.atualizarUsuario(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Apagar", description = "Apagar usuario")
    public ResponseEntity<Void> apagarUsuario(@PathVariable UUID id) {
        usuarioService.apagarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

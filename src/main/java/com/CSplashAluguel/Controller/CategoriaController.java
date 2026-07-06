package com.CSplashAluguel.Controller;

import com.CSplashAluguel.DTO.Request.CategoriaRequest;
import com.CSplashAluguel.DTO.Response.CategoriaResponse;
import com.CSplashAluguel.Service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Categoria")
@CrossOrigin("*")

public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;


    @GetMapping

    @Operation(summary = "Listar", description = "Listar Categoria de carros")
    public ResponseEntity<List<CategoriaResponse>> listarCategoriadeCarro() {
        List<CategoriaResponse> listarCategoria = categoriaService.listarCategoria();
        return new ResponseEntity<>(listarCategoria, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Listar Id", description = "Listar Categoria de carros por id")
    public ResponseEntity<CategoriaResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(categoriaService.listarPorId(id), HttpStatus.OK);
    }

    @PostMapping

    @Operation(summary = "Criar", description = "Cadastrar categoria de Carros")
    public ResponseEntity<CategoriaResponse> criarCategoriaDeCrro(@RequestBody @Valid CategoriaRequest request) {
        return new ResponseEntity<>(categoriaService.criarCategoria(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")

    @Operation(summary = "Atulizar", description = "Atualizar Categoria de carros")
    public ResponseEntity<CategoriaResponse> atualizarCategoriaCarro(@PathVariable UUID id, @RequestBody CategoriaRequest request) {
        return new ResponseEntity<>(categoriaService.atualizarCategoria(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Apagar", description = "Apagar Categoria de carros por id")
    public ResponseEntity<Void> apagarCarro(@PathVariable UUID id) {
        categoriaService.apagar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
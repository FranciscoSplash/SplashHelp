package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.CarroRequest;
import com.clinica.CSplash.DTO.Request.CategoriaRequest;
import com.clinica.CSplash.DTO.Response.CarroResponse;
import com.clinica.CSplash.DTO.Response.CategoriaResponse;
import com.clinica.CSplash.Service.CarroService;
import com.clinica.CSplash.Service.CategoriaService;
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
    public ResponseEntity<List<CategoriaResponse>> listarCategoriadeCarro() {
        List<CategoriaResponse> listarCategoria = categoriaService.listarCategoria();
        return new ResponseEntity<>(listarCategoria, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(categoriaService.listarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoriaDeCrro(@RequestBody CategoriaRequest request) {
        return new ResponseEntity<>(categoriaService.criarCategoria(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoriaCarro(@PathVariable UUID id, @RequestBody CategoriaRequest request) {
        return new ResponseEntity<>(categoriaService.atualizarCategoria(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCarro(@PathVariable UUID id) {
        categoriaService.apagar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
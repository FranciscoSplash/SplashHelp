package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.CategoriaRequest;
import com.clinica.CSplash.DTO.Request.DocumentosRequest;
import com.clinica.CSplash.DTO.Response.CategoriaResponse;
import com.clinica.CSplash.DTO.Response.DocumentResponse;
import com.clinica.CSplash.Service.CategoriaService;
import com.clinica.CSplash.Service.DocumentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Documentacao")

public class DocumentacaoController {

    @Autowired
    private DocumentosService documentosService;


    @GetMapping
    public ResponseEntity<List<DocumentResponse>> listartodosDocumentos() {
        List<DocumentResponse> listarDocumentos = documentosService.listar();
        return new ResponseEntity<>(listarDocumentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(documentosService.litarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocumentResponse> criarDocumento(@RequestBody DocumentosRequest request) {
        return new ResponseEntity<>(documentosService.criarDocumento(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> atualizarDocumentos(@PathVariable UUID id, @RequestBody DocumentosRequest request) {
        return new ResponseEntity<>(documentosService.atualizarDocumento(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCarro(@PathVariable UUID id) {
        documentosService.apagar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

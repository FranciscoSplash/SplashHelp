package com.CSplashAluguel.Controller;

import com.CSplashAluguel.DTO.Request.DocumentosRequest;
import com.CSplashAluguel.DTO.Response.DocumentResponse;
import com.CSplashAluguel.Service.DocumentosService;
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
@RequestMapping("/api/Documentacao")

public class DocumentacaoController {

    @Autowired
    private DocumentosService documentosService;


    @GetMapping

    @Operation(summary = "Listar", description = "Lista todos os documentos")
    public ResponseEntity<List<DocumentResponse>> listartodosDocumentos() {
        List<DocumentResponse> listarDocumentos = documentosService.listar();
        return new ResponseEntity<>(listarDocumentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Listar Id", description = "Listar por id Documento")
    public ResponseEntity<DocumentResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(documentosService.litarPorId(id), HttpStatus.OK);
    }

    @PostMapping

    @Operation(summary = "Criar", description = "Cadastrar Documentos")
    public ResponseEntity<DocumentResponse> criarDocumento(@RequestBody @Valid DocumentosRequest request) {
        return new ResponseEntity<>(documentosService.criarDocumento(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")

    @Operation(summary = "Atulizar", description = "Atualizar Documentos")
    public ResponseEntity<DocumentResponse> atualizarDocumentos(@PathVariable UUID id, @RequestBody DocumentosRequest request) {
        return new ResponseEntity<>(documentosService.atualizarDocumento(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Apagar", description = "Atualizar Documentos")
    public ResponseEntity<Void> apagarCarro(@PathVariable UUID id) {
        documentosService.apagar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

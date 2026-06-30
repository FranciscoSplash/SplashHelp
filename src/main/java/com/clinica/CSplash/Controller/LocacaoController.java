package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.DocumentosRequest;
import com.clinica.CSplash.DTO.Request.LocacaoRequest;
import com.clinica.CSplash.DTO.Response.DocumentResponse;
import com.clinica.CSplash.DTO.Response.LocacaoResponse;
import com.clinica.CSplash.Service.DocumentosService;
import com.clinica.CSplash.Service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/locacao")

public class LocacaoController {


    @Autowired
    private LocacaoService locacaoService;


    @GetMapping
    public ResponseEntity<List<LocacaoResponse>> listartodasLocacao() {
        List<LocacaoResponse> listarLocacao = locacaoService.listarLocacao();
        return new ResponseEntity<>(listarLocacao, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(locacaoService.listarPoId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocacaoResponse> criarLocacao(@RequestBody LocacaoRequest request) {
        return new ResponseEntity<>(locacaoService.criarLocacao(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocacaoResponse> atualizarLocacao(@PathVariable UUID id, @RequestBody LocacaoRequest request) {
        return new ResponseEntity<>(locacaoService.atualizarlocacao(id, request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/retirar")
    public ResponseEntity<LocacaoResponse> confirmarRetirada(@PathVariable UUID id) {
        return new ResponseEntity<>(locacaoService.confirmarRetirada(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<LocacaoResponse> concluirDevolucao(@PathVariable UUID id) {
        return new ResponseEntity<>(locacaoService.concluirDevolucao(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<LocacaoResponse> cancelarLocacao(@PathVariable UUID id) {
        return new ResponseEntity<>(locacaoService.cancelarLocacao(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarLocacao(@PathVariable UUID id) {
        locacaoService.apagarLocacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

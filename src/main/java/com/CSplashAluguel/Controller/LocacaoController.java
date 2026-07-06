package com.CSplashAluguel.Controller;

import com.CSplashAluguel.DTO.Request.LocacaoRequest;
import com.CSplashAluguel.DTO.Request.RetirarRequest;
import com.CSplashAluguel.DTO.Response.LocacaoResponse;
import com.CSplashAluguel.Service.LocacaoService;
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
@RequestMapping("/api/locacao")

public class LocacaoController {


    @Autowired
    private LocacaoService locacaoService;


    @GetMapping

    @Operation(summary = "listar", description = "Listar todas as Locações")
    public ResponseEntity<List<LocacaoResponse>> listartodasLocacao() {
        List<LocacaoResponse> listarLocacao = locacaoService.listarLocacao();
        return new ResponseEntity<>(listarLocacao, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    @Operation(summary = "Listar Id", description = "Listar por id")
    public ResponseEntity<LocacaoResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(locacaoService.listarPoId(id), HttpStatus.OK);
    }

    @PostMapping

    @Operation(summary = "Criar", description = "Cadastrar Locacao")
    public ResponseEntity<LocacaoResponse> criarLocacao(@Valid  @RequestBody LocacaoRequest request) {
        return new ResponseEntity<>(locacaoService.criarLocacao(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")

    @Operation(summary = "Atualizarr", description = "Atualizar Locacao")
    public ResponseEntity<LocacaoResponse> atualizarLocacao(@PathVariable UUID id, @RequestBody LocacaoRequest request) {
        return new ResponseEntity<>(locacaoService.atualizarlocacao(id, request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/retirar")

    @Operation(summary = "Confirmar", description = "Confirmar Retirada do CArro")
    public ResponseEntity<LocacaoResponse> confirmarRetirada(@PathVariable  UUID id, @RequestBody RetirarRequest request) {
        return new ResponseEntity<>(locacaoService.confirmarRetirada(id,request), HttpStatus.OK);
    }

    @GetMapping("/retirar/lista")

    @Operation(summary = "Listar", description = "Listar todas as retiradas do Carro")
    public ResponseEntity<List<LocacaoResponse>>ListarConfirmadosRetirada() {
        return new ResponseEntity<>(locacaoService.listarConfirmadosLocacao(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/devolver")

    @Operation(summary = "Concluir", description = "Concluir devolucao")
    public ResponseEntity<LocacaoResponse> concluirDevolucao(@PathVariable UUID id, @RequestBody LocacaoRequest request) {
        return new ResponseEntity<>(locacaoService.concluirDevolucao(id, request), HttpStatus.OK);
    }

    @PutMapping("/{id}/cancelar")

    @Operation(summary = "Cancelar", description = "Cancelar Locacao")
    public ResponseEntity<LocacaoResponse> cancelarLocacao(@PathVariable UUID id, @RequestBody CanccelarLocacaoRequest request) {
        return new ResponseEntity<>(locacaoService.cancelarLocacao(id, request), HttpStatus.OK);
    }

    @GetMapping("/cancelar/listar")

    @Operation(summary = "Listar", description = "Listar toda Lista de Cancelamento")
    public ResponseEntity <List<LocacaoResponse>>ListarCancelados() {
        return new ResponseEntity<>(locacaoService.listarCancedosLocacao(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Apagar", description = "Apagar Locacao")
    public ResponseEntity<Void> apagarLocacao(@PathVariable UUID id) {
        locacaoService.apagarLocacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

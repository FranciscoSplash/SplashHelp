package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.PagamentoRequest;
import com.clinica.CSplash.DTO.Response.PagamentoResponse;
import com.clinica.CSplash.Service.PagamentoService;
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
@RequestMapping("/api/pagamento")
public class PagamentoController {


    @Autowired
    private PagamentoService pagamentoService;


    @GetMapping

    @Operation(summary = "Listar", description = "Listar todos os Pagamentos")
    public ResponseEntity<List<PagamentoResponse>> listartodosPagamentos() {
        List<PagamentoResponse> listarPagamentos = pagamentoService.listarTodosPagamentos();
        return new ResponseEntity<>(listarPagamentos, HttpStatus.OK);
    }


    @PostMapping("/gerarpagamento")

    @Operation(summary = "Criar", description = "Gerar Pagamento")
    public ResponseEntity<PagamentoResponse> gerarPagamento( @Valid @RequestBody PagamentoRequest request) {
        return new ResponseEntity<>(pagamentoService.gerarIntencaoPagamento(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/confirmar")

    @Operation(summary = "Confirmar", description = "Confirmar Pagamento")
    public ResponseEntity<PagamentoResponse> confirmarPagamento(@PathVariable UUID id ,@Valid @RequestBody PagamentoRequest request) {
        return new ResponseEntity<>(pagamentoService.confirmarPagamento(id, request), HttpStatus.OK);

    }
}

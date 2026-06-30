package com.clinica.CSplash.Controller;

import com.clinica.CSplash.DTO.Request.PagamentoRequest;
import com.clinica.CSplash.DTO.Response.PagamentoResponse;
import com.clinica.CSplash.Service.PagamentoService;
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
    public ResponseEntity<List<PagamentoResponse>> listartodosPagamentos() {
        List<PagamentoResponse> listarPagamentos = pagamentoService.listarTodosPagamentos();
        return new ResponseEntity<>(listarPagamentos, HttpStatus.OK);
    }


    @PostMapping("/pagar")
    public ResponseEntity<PagamentoResponse> gerarPagamento(@RequestBody PagamentoRequest request) {
        return new ResponseEntity<>(pagamentoService.gerarIntencaoPagamento(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<PagamentoResponse> confirmarPagamento(@PathVariable UUID id) {
        return new ResponseEntity<>(pagamentoService.confirmarPagamento(id), HttpStatus.OK);

    }
}

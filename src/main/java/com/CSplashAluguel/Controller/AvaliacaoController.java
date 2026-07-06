package com.CSplashAluguel.Controller;


import com.CSplashAluguel.DTO.Request.AvalicaoRequest;
import com.CSplashAluguel.DTO.Response.AvaliacaoResponse;
import com.CSplashAluguel.Service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/avaliacao")
@CrossOrigin("*")

public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    @Operation(summary = "listar as avalições", description = "Lista todas avaliacões")
    public ResponseEntity<List<AvaliacaoResponse>>listaravaliacao() {


        List<AvaliacaoResponse> listar = avaliacaoService.listarAvaliacao();

        return new ResponseEntity<>(listar, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "listar por id", description = "Lista as avaliacões por id")
    public ResponseEntity<AvaliacaoResponse>listarAvaliacaoPorId(@PathVariable UUID id){

        return new ResponseEntity<>(avaliacaoService.listarAvaliacaoPorId(id),HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Criar Avaliacao", description = "Cria Comentarios")

    public ResponseEntity<AvaliacaoResponse>criarAvaliacao(@RequestBody @Valid AvalicaoRequest request){
        return new ResponseEntity<>(avaliacaoService.criarAvalicao(request),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar", description = "Apaga os comentarios por id")
    public ResponseEntity<Void>apagarAvaliacao(@PathVariable UUID id){
        avaliacaoService.apagarAvaliacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

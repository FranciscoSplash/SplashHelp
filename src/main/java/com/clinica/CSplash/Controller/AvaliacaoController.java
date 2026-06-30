package com.clinica.CSplash.Controller;


import com.clinica.CSplash.DTO.Request.AvalicaoRequest;
import com.clinica.CSplash.DTO.Response.AvaliacaoResponse;
import com.clinica.CSplash.Service.AvaliacaoService;
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
    public ResponseEntity<List<AvaliacaoResponse>>listaravaliacao() {


        List<AvaliacaoResponse> listar = avaliacaoService.listarAvaliacao();

        return new ResponseEntity<>(listar, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse>listarAvaliacaoPorId(@PathVariable UUID id){

        return new ResponseEntity<>(avaliacaoService.listarAvaliacaoPorId(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponse>criarAvaliacao(@RequestBody AvalicaoRequest request){
        return new ResponseEntity<>(avaliacaoService.criarAvalicao(request),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>apagarAvaliacao(@PathVariable UUID id){
        avaliacaoService.apagarAvaliacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

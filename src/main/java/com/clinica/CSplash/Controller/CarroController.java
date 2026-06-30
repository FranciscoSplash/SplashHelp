package com.clinica.CSplash.Controller;


import com.clinica.CSplash.DTO.Request.CarroRequest;
import com.clinica.CSplash.DTO.Response.CarroResponse;
import com.clinica.CSplash.Service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin("*")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping
    public ResponseEntity<List<CarroResponse>>listarCarros(){
        List<CarroResponse>listarCarro=carroService.listarCarros();
        return new ResponseEntity<>(listarCarro, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarroResponse>listarPorId(@PathVariable UUID id){
        return new ResponseEntity<>(carroService.listarPorId(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CarroResponse>criarCarro(@RequestBody CarroRequest request){
        return new ResponseEntity<>(carroService.cadastrarCarro(request),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponse>atualizarCarro(@PathVariable UUID id, @RequestBody  CarroRequest request){
        return new ResponseEntity<>(carroService.atualizarCarro(id,request),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>apagarCarro(@PathVariable  UUID id){
       carroService.apgarCarro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

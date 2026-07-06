package com.CSplashAluguel.Controller;


import com.CSplashAluguel.DTO.Request.CarroRequest;
import com.CSplashAluguel.DTO.Response.CarroResponse;
import com.CSplashAluguel.Service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin("*")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping

    @Operation(summary = "Listar", description = "Listar todos oscarros")
    public ResponseEntity<List<CarroResponse>>listarCarros(){
        List<CarroResponse>listarCarro=carroService.listarCarros();
        return new ResponseEntity<>(listarCarro, HttpStatus.OK);
    }
    @GetMapping("/{id}")

    @Operation(summary = "Listar Id", description = "Listar por id carros ")
    public ResponseEntity<CarroResponse>listarPorId(@PathVariable UUID id){
        return new ResponseEntity<>(carroService.listarPorId(id),HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    @Operation(summary = "Criar", description = "Cadastrar carros")
    public ResponseEntity<CarroResponse>criarCarro(@RequestPart("request")
                                                       @Valid CarroRequest request, @RequestPart("imagem") MultipartFile imagem) throws IOException {
        return new ResponseEntity<>(carroService.cadastrarCarro(request, imagem),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")

    @Operation(summary = "Atualizar", description = "Atualizar por Id")
    public ResponseEntity<CarroResponse>atualizarCarro(@PathVariable UUID id, @RequestBody  CarroRequest request){
        return new ResponseEntity<>(carroService.atualizarCarro(id,request),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")

    @Operation(summary = "apagar", description = "Apagar carros por id")
    public ResponseEntity<Void>apagarCarro(@PathVariable  UUID id){
       carroService.apgarCarro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

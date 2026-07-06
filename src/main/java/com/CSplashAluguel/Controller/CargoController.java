package com.CSplashAluguel.Controller;


import com.CSplashAluguel.DTO.Request.CargoRequest;
import com.CSplashAluguel.DTO.Response.CargoResponse;
import com.CSplashAluguel.Service.CargoService;
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
@RequestMapping("/api/cargo")

public class CargoController {

    @Autowired
    private CargoService cargoService;


    @GetMapping
    @Operation(summary = "listar ", description = "Lista Cargos")
    public ResponseEntity<List<CargoResponse>> listarCargos(){
        return new ResponseEntity<>(cargoService.listarCargo(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "listar por id", description = "Lista os cargos existente por id")
    public ResponseEntity<CargoResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(cargoService.listarPorId(id), HttpStatus.OK);
    }
    @PostMapping
    @Operation(summary = "criar", description = "Criar cargos")
    public ResponseEntity<CargoResponse> criarCargo(@RequestBody @Valid CargoRequest request) {
        return new ResponseEntity<>(cargoService.criarCargo(request), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualiza os cargos por id")
    public ResponseEntity<CargoResponse> atualizarCargo(@PathVariable UUID id,  @RequestBody CargoRequest request) {
        return new ResponseEntity<>(cargoService.atualizarCargo(id, request), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Apagar por id", description = "Apaga Cargos por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCargo(@PathVariable UUID id) {
        cargoService.apagarCargo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

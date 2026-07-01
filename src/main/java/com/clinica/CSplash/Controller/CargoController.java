package com.clinica.CSplash.Controller;


import com.clinica.CSplash.DTO.Request.CargoRequest;
import com.clinica.CSplash.DTO.Request.UsuarioRequest;
import com.clinica.CSplash.DTO.Response.CargoResponse;
import com.clinica.CSplash.DTO.Response.UsuarioResponse;
import com.clinica.CSplash.Service.CargoService;
import com.clinica.CSplash.Service.UsuarioService;
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
    public ResponseEntity<List<CargoResponse>> listarCargos(){
        return new ResponseEntity<>(cargoService.listarCargo(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CargoResponse> listarPorId(@PathVariable UUID id) {
        return new ResponseEntity<>(cargoService.listarPorId(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CargoResponse> criarCargo(@RequestBody CargoRequest request) {
        return new ResponseEntity<>(cargoService.criarCargo(request), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CargoResponse> atualizarCargo(@PathVariable UUID id, @RequestBody CargoRequest request) {
        return new ResponseEntity<>(cargoService.atualizarCargo(id, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarCargo(@PathVariable UUID id) {
        cargoService.apagarCargo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.clinica.CSplash.Service;


import com.clinica.CSplash.DTO.Request.CargoRequest;
import com.clinica.CSplash.DTO.Response.CargoResponse;
import com.clinica.CSplash.Model.Cargo;
import com.clinica.CSplash.Repository.CargoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;


    public List<CargoResponse> listarCargo(){
        return cargoRepository.findAll().stream().map(this::toResponse).toList();
    }


    public CargoResponse listarPorId(UUID id){
        Cargo cargo=cargoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id inexistente"));

        return toResponse(cargo);
    }

    public CargoResponse criarCargo(CargoRequest request){
        Cargo cargo=new Cargo();
        cargo.setNomeDoCargo(request.nomeDoCargo());


        return toResponse(cargoRepository.save(cargo));

    }

    public CargoResponse atualizarCargo(UUID id, CargoRequest request){

        Cargo cargo=cargoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id inexistente"));

        cargo.setNomeDoCargo(request.nomeDoCargo());


        return toResponse(cargoRepository.save(cargo));

    }

    public void apagarCargo(UUID id){

        Cargo cargo=cargoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id inexistente"));

        cargoRepository.delete(cargo);


    }

    public CargoResponse toResponse(Cargo cargo){
        return  new CargoResponse(
                cargo.getId(),
                cargo.getNomeDoCargo()
        );
    }
}

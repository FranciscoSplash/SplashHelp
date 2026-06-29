package com.clinica.CSplash.Service;

import com.clinica.CSplash.DTO.Request.CarroRequest;
import com.clinica.CSplash.DTO.Response.CarroResponse;
import com.clinica.CSplash.Model.Carro;
import com.clinica.CSplash.Model.Enum.StatusCarro;
import com.clinica.CSplash.Repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;




    public CarroResponse cadastrarCarro(CarroRequest request){


        if(carroRepository.existsByPlaca(request.placa())){
            throw new IllegalArgumentException("A placa do Carro ja existe");
        }


        Carro carro=new Carro();
        carro.setCategoria(request.categoria());
        carro.setMarca(request.marca());
        carro.setCor(request.cor());
        carro.setModelo(request.modelo());
        carro.setAno(request.ano());
        carro.setPlaca(request.placa());
        carro.setPrecoDia(request.preco());
        carro.setStatusCarro(StatusCarro.LIVRE);

        return toResponse(carroRepository.save(carro));

    }

    public List<CarroResponse> listarCarros(){

        return carroRepository.findAll().stream().map(this::toResponse).toList();
    }
    public CarroResponse listarPorId(UUID id){
        Carro carro= carroRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Id não Encontrado"));

        return toResponse(carro);
    }


    public  void apgarCarro(UUID id){
        Carro carro=carroRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Id inexistente"));

        carroRepository.delete(carro);
    }
    public CarroResponse toResponse(Carro carro){
        return new CarroResponse(
                carro.getId(),
                carro.getCategoria(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getCor(),
                carro.getAno(),
                carro.getPlaca(),
                carro.getPrecoDia(),
                carro.getStatusCarro()

        );
    }
}

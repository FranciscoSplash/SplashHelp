package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Request.CarroRequest;
import com.CSplashAluguel.DTO.Response.CarroResponse;
import com.CSplashAluguel.Model.Carro;
import com.CSplashAluguel.Model.Enum.StatusCarro;
import com.CSplashAluguel.Repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;




    public CarroResponse cadastrarCarro(CarroRequest request, MultipartFile imagen) throws IOException {


        if(carroRepository.existsByPlaca(request.placa())){
            throw new IllegalArgumentException("A placa do Carro ja existe");
        }


        String pasta="upload/carros/";
        Files.createDirectories(Paths.get(pasta));
        String nomeDoArquivo =UUID.randomUUID() + "_"+ imagen.getOriginalFilename();
        Path caminho = Paths.get(pasta, nomeDoArquivo);

        Files.write(caminho, imagen.getBytes());

        Carro carro=new Carro();
        carro.setCategoria(request.categoria());
        carro.setMarca(request.marca());
        carro.setCor(request.cor());
        carro.setModelo(request.modelo());
        carro.setAno(request.ano());
        carro.setPlaca(request.placa());
        carro.setImagemUrl(pasta + nomeDoArquivo);
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

  public  CarroResponse atualizarCarro(UUID id, CarroRequest request ) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id inexistente"));


        carro.setCategoria(request.categoria());
        carro.setMarca(request.marca());
        carro.setCor(request.cor());
        carro.setModelo(request.modelo());
        carro.setAno(request.ano());
        carro.setPlaca(request.placa());
        carro.setImagemUrl(request.imagemUrl());
        carro.setPrecoDia(request.preco());
        carro.setStatusCarro(StatusCarro.LIVRE);

        return toResponse(carroRepository.save(carro));

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
                carro.getImagemUrl(),
                carro.getPrecoDia(),
                carro.getStatusCarro()

        );
    }
}

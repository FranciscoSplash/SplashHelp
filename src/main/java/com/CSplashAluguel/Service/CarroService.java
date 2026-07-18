package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Request.CarroRequest;
import com.CSplashAluguel.DTO.Response.CarroResponse;
import com.CSplashAluguel.DTO.Response.MapsResponse;
import com.CSplashAluguel.Model.Carro;
import com.CSplashAluguel.Model.Enum.StatusCarro;
import com.CSplashAluguel.Repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private MapsService mapsService;


    public CarroResponse cadastrarCarro(CarroRequest request, MultipartFile imagen) throws IOException {


        if(carroRepository.existsByPlaca(request.placa())){
            throw new IllegalArgumentException("A placa do Carro ja existe");
        }

//Criar um ficheiro
        String pasta="upload/carros/";
        Files.createDirectories(Paths.get(pasta));
        String nomeDoArquivo =UUID.randomUUID() + "_"+ imagen.getOriginalFilename();
        Path caminho = Paths.get(pasta, nomeDoArquivo);

        Files.write(caminho, imagen.getBytes());

        System.out.println("Salvando em: " + caminho.toAbsolutePath());
//1. O Java chama a API de mapas enviando o texto do endereço
//        // A API externa vai traduzir o texto em números de GPS

        MapsResponse maps=mapsService.buscar(request.endereco());
        Carro carro=new Carro();
        carro.setCategoria(request.categoria());
        carro.setMarca(request.marca());
        carro.setCor(request.cor());
        carro.setModelo(request.modelo());
        carro.setAno(request.ano());
        carro.setPlaca(request.placa());
        carro.setEndereco(request.endereco());
        carro.setImagemUrl(pasta + nomeDoArquivo);
        carro.setPrecoDia(request.preco());
        carro.setStatusCarro(StatusCarro.LIVRE);

        carro.setLat(maps.lat());
        carro.setLongi(maps.lon());

        return toResponse(carroRepository.save(carro));

    }

    public List<CarroResponse> listarCarros(){

        Sort sort= Sort.by("nome").descending().and(Sort.by("preco").ascending());
        return carroRepository.findAll(sort).stream().map(this::toResponse).toList();
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
        carro.setEndereco(request.endereco());

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
                carro.getCor(),
                carro.getModelo(),
                carro.getAno(),
                carro.getPlaca(),
                carro.getEndereco(),
                carro.getImagemUrl(),
                carro.getPrecoDia(),
                carro.getStatusCarro()

        );
    }
}

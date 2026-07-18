package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Response.CarroResponse;
import com.CSplashAluguel.Model.Carro;
import com.CSplashAluguel.Model.Enum.StatusCarro;
import com.CSplashAluguel.Repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroProximoService {

    private CarroRepository carroRepository;

    public CarroProximoService(CarroRepository carroRepository){

        this.carroRepository =carroRepository;
    }
    public List<CarroResponse> buscarCarroProximo(Double latUsuario,Double longiUsuario, Double raioMaximo){
        // 1. Busca apenas os carros que estão em modo "Disponível"
        List<Carro>carrosDispponiveis=carroRepository.findByStatusCarro(StatusCarro.LIVRE);

        // 2. Filtra e ordena usando a fórmula matemática de distância
        return carrosDispponiveis.stream().filter(
                carro ->{ Double distancia=calcularDistancia(latUsuario, longiUsuario, carro.getLat(),carro.getLongi());
                    return distancia<=raioMaximo;
                })

        .sorted((c1, c2)->{
                    Double dist1 = calcularDistancia(latUsuario, longiUsuario, c1.getLat(), c1.getLongi());
                    Double dist2 = calcularDistancia(latUsuario, longiUsuario, c2.getLat(), c2.getLongi());
                    return dist1.compareTo(dist2); // Ordena do mais perto para o mais longe

                }).map(carro -> new CarroResponse(
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
                ))
                .collect(Collectors.toList());
}

    // calcular a distância em Quilômetros (Fórmula de Haversine)
    private Double calcularDistancia(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371; // Raio da Terra em km

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Retorna a distância em Quilômetros
    }
}
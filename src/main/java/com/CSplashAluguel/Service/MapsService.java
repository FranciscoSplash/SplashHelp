package com.CSplashAluguel.Service;


import com.CSplashAluguel.DTO.Response.MapsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MapsService {


    private RestClient restClient;

    //Configurando a URL base de um serviço de mapas
    public MapsService(RestClient.Builder builder){
        this.restClient=builder.baseUrl("https://nominatim.openstreetmap.org").build();
    }

    public MapsResponse buscar(String endereco){

        return  restClient.get()

                // Faz a busca reversa: envia a lat/long e recebe os dados de endereço se precisar

                .uri(uriBuilder -> uriBuilder.
                        path("/search").queryParam("q", endereco)
                        .queryParam("format","json")
                        .queryParam("limit", "1")   // Pega apenas o primeiro resultado mais relevante
                        .build())
                .retrieve()
                .body(MapsResponse[].class)[0];           // O Nominatim retorna um Array [], pegamos a primeira posição [0]
    }
}

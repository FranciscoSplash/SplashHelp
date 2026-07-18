package com.CSplashAluguel.DTO.Response;

import com.CSplashAluguel.Model.Categoria;
import com.CSplashAluguel.Model.Enum.StatusCarro;

import java.math.BigDecimal;
import java.util.UUID;

public record CarroResponse(
        UUID id,
        Categoria categoria,
        String marca,
        String cor,
        String modelo,
        Integer ano,
        String placa,
        String endereco,
        String imagemUrl,
        BigDecimal preco,
        StatusCarro statusCarro
) {
}

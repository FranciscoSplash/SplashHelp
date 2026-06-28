package com.clinica.CSplash.DTO.Response;

import com.clinica.CSplash.Model.Categoria;
import com.clinica.CSplash.Model.Enum.StatusCarro;

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
        BigDecimal preco,
        StatusCarro statusCarro
) {
}

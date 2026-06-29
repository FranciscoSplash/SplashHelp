package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Categoria;
import com.clinica.CSplash.Model.Enum.StatusCarro;

import java.math.BigDecimal;

public record CarroRequest(
        Categoria categoria,
        String marca,
        String cor,
        String modelo,
        Integer ano,
        String placa,
        BigDecimal preco,
        StatusCarro statusCarro
)

{
}

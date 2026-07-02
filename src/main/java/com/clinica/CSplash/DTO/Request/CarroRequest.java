package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Categoria;
import com.clinica.CSplash.Model.Enum.StatusCarro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CarroRequest(
        @NotNull(message = "Categoria é Obrigatoriio")
        Categoria categoria,

        @NotBlank(message = "A marca é obrigatorio")
        String marca,
        String cor,
        @Size(max = 20)
        String modelo,
        @NotNull(message = "O ano do lancamento do carro é importante")
        Integer ano,

        @NotBlank(message = "Aplaca é obrigatorio")
        String placa,

        @Positive
        BigDecimal preco,

        @NotBlank(message = "Estado do Carro obrigatorio")
        StatusCarro statusCarro
)

{
}

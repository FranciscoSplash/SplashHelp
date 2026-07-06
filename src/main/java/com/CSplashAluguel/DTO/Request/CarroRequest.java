package com.CSplashAluguel.DTO.Request;

import com.CSplashAluguel.Model.Categoria;
import com.CSplashAluguel.Model.Enum.StatusCarro;
import jakarta.persistence.Column;
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

        @NotBlank(message = "Cor é obrigatorio")
        String cor,

        @Size(max = 20, message = "maximo 20 caracteres")
        String modelo,

        @NotNull(message = "O ano do lancamento do carro é importante")
        Integer ano,

        @NotBlank(message = "Aplaca é obrigatorio")
        @Column(unique = true)
        String placa,

        @Positive(message = "Preco maior que 0")
        BigDecimal preco,


        String imagemUrl,
        @NotNull(message = "Estado do Carro obrigatorio")
        StatusCarro statusCarro
)

{
}

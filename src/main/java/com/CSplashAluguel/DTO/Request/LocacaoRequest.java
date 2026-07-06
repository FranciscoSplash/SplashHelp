package com.CSplashAluguel.DTO.Request;

import com.CSplashAluguel.Model.Enum.StatusLocacao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LocacaoRequest(

        @NotNull(message = "Data de validade Obrigatorio")
        @FutureOrPresent(message = "A Data do inicio não pode estar no passado ")
        LocalDateTime dataInicio,

        @NotNull(message = "Data de validade Obrigatorio")
        @Future(message = "A data so pode estar no futuro")
        LocalDateTime dataFim,

        @NotNull(message = "Data de validade Obrigatorio")
        @Positive(message = "Não Pode ser um valor negativo")
        BigDecimal preco,

        @NotNull(message = "Id do Usuario obrigatorio")
        UUID usuarioId,

        @NotNull(message = "Id do Carro obrigatorio")
        UUID carroId,


        @NotNull(message = "Data de validade Obrigatorio")
        StatusLocacao statusLocacao
) {
}

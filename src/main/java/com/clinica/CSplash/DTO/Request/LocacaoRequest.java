package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Carro;
import com.clinica.CSplash.Model.Enum.StatusLocacao;
import com.clinica.CSplash.Model.Usuario;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LocacaoRequest(
        @NotNull(message = "Data de validade Obrigatorio")
        LocalDateTime dataInicio,

        @NotNull(message = "Data de validade Obrigatorio")
        LocalDateTime dataFim,
        BigDecimal preco,
        UUID usuarioId,
        UUID carroId,


        @NotNull(message = "Data de validade Obrigatorio")
        StatusLocacao statusLocacao
) {
}

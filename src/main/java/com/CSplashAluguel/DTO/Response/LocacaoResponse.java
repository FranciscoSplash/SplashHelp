package com.CSplashAluguel.DTO.Response;

import com.CSplashAluguel.Model.Carro;
import com.CSplashAluguel.Model.Enum.StatusLocacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LocacaoResponse(
        UUID id,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        BigDecimal preco,
        UsuarioResponse usuario,
        Carro carro,
        StatusLocacao statusLocacao
) {
}

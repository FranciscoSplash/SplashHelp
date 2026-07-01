package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Carro;
import com.clinica.CSplash.Model.Enum.StatusLocacao;
import com.clinica.CSplash.Model.Usuario;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LocacaoRequest(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        BigDecimal preco,
        UUID usuarioId,
        UUID carroId,
        StatusLocacao statusLocacao
) {
}

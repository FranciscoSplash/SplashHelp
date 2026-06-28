package com.clinica.CSplash.DTO.Response;

import com.clinica.CSplash.Model.Enum.StatusLocacao;
import com.clinica.CSplash.Model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LocacaoResponse(
        UUID id,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        BigDecimal preco,
        Usuario usuario,
        StatusLocacao statusLocacao
) {
}

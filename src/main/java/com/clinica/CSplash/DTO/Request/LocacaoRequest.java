package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Carro;
import com.clinica.CSplash.Model.Enum.StatusLocacao;
import com.clinica.CSplash.Model.Usuario;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LocacaoRequest(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        BigDecimal preco,
        Usuario usuario,
        Carro carro,
        StatusLocacao statusLocacao
) {
}

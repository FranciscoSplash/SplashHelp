package com.clinica.CSplash.DTO.Response;

import com.clinica.CSplash.Model.Enum.MetodoPagamento;
import com.clinica.CSplash.Model.Enum.StatusPagamento;
import com.clinica.CSplash.Model.Locacao;

import java.util.UUID;

public record PagamentoResponse(
        UUID id,
        Locacao locacao,

        MetodoPagamento metodoPagamento,

        StatusPagamento statusPagamento
) {
}

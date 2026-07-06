package com.CSplashAluguel.DTO.Response;

import com.CSplashAluguel.Model.Enum.MetodoPagamento;
import com.CSplashAluguel.Model.Enum.StatusPagamento;
import com.CSplashAluguel.Model.Locacao;

import java.util.UUID;

public record PagamentoResponse(
        UUID id,
        Locacao locacao,

        MetodoPagamento metodoPagamento,

        StatusPagamento statusPagamento
) {
}

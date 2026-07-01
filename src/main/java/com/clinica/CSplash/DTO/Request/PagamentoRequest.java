package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.MetodoPagamento;
import com.clinica.CSplash.Model.Enum.StatusPagamento;
import com.clinica.CSplash.Model.Locacao;

import java.util.UUID;

public record PagamentoRequest(
         UUID locacaoId,

        MetodoPagamento metodoPagamento,

        StatusPagamento statusPagamento
) {
}

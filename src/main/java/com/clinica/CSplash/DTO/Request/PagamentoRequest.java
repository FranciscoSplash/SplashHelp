package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.MetodoPagamento;
import com.clinica.CSplash.Model.Enum.StatusPagamento;
import com.clinica.CSplash.Model.Locacao;

public record PagamentoRequest(
         Locacao locacao,

        MetodoPagamento metodoPagamento,

        StatusPagamento statusPagamento
) {
}

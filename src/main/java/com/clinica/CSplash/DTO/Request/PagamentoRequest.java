package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.MetodoPagamento;
import com.clinica.CSplash.Model.Enum.StatusPagamento;
import com.clinica.CSplash.Model.Locacao;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PagamentoRequest(
         UUID locacaoId,


         @NotNull(message = "Campo de pagamento obrigatorio")
        MetodoPagamento metodoPagamento,


         @NotNull(message = "Campo de estado obrigatorio")
        StatusPagamento statusPagamento
) {
}

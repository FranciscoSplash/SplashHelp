package com.CSplashAluguel.DTO.Request;

import com.CSplashAluguel.Model.Enum.MetodoPagamento;
import com.CSplashAluguel.Model.Enum.StatusPagamento;
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

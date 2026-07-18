package com.CSplashAluguel.DTO.Request;

import com.CSplashAluguel.Model.Enum.StatusLocacao;

public record CanccelarLocacaoRequest(
        StatusLocacao statusLocacao
) {
}

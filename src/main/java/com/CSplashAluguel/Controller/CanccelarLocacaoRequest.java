package com.CSplashAluguel.Controller;

import com.CSplashAluguel.Model.Enum.StatusLocacao;

public record CanccelarLocacaoRequest(
        StatusLocacao statusLocacao
) {
}

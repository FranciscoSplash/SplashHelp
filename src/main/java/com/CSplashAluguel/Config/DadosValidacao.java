package com.CSplashAluguel.Config;

import java.time.LocalDateTime;

public record DadosValidacao(
        LocalDateTime timestamp,
        int status,
        String error,
        String messeng

) {
}

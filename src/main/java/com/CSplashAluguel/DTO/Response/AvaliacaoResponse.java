package com.CSplashAluguel.DTO.Response;

import com.CSplashAluguel.Model.Locacao;
import com.CSplashAluguel.Model.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record AvaliacaoResponse(
        UUID id,
        Locacao locacao,
        Usuario usuario,
        Integer nota,
        String comentario,
        LocalDateTime criadoEm
) {
}

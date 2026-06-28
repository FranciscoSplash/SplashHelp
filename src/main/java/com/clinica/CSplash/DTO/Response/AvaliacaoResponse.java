package com.clinica.CSplash.DTO.Response;

import com.clinica.CSplash.Model.Locacao;
import com.clinica.CSplash.Model.Usuario;

import java.util.UUID;

public record AvaliacaoResponse(
        UUID id,
        Locacao locacao,
        Usuario usuario,
        Integer nota,
        String comentario
) {
}

package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Locacao;
import com.clinica.CSplash.Model.Usuario;

public record AvalicaoRequest(
        Locacao locacao,
        Usuario usuario,
        Integer nota,
        String comentario
) {
}

package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Locacao;
import com.clinica.CSplash.Model.Usuario;
import jakarta.persistence.Id;

import java.util.UUID;

public record AvalicaoRequest(
        UUID locacaoId,
        Integer nota,
        String comentario
) {
}

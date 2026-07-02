package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Locacao;
import com.clinica.CSplash.Model.Usuario;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AvalicaoRequest(
        UUID locacaoId,

        @Max(10)
        Integer nota,
        @Size(min = 5, max=100)
        String comentario
) {
}

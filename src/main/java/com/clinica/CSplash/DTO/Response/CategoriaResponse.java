package com.clinica.CSplash.DTO.Response;

import java.util.UUID;

public record CategoriaResponse(
        UUID id,
        String nomeDaCategoria,

        String descricao
) {
}

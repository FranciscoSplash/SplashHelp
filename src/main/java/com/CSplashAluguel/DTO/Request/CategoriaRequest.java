package com.CSplashAluguel.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
        @NotBlank(message = "Nome da Categoria Obrigatorio")
        String nomeDaCategoria,

        @NotBlank(message = "Nome da Categoria Obrigatorio")
        @Size(max=100, message = "Deve ter pelo menos 100 caracteres")
         String descricao
) {
}

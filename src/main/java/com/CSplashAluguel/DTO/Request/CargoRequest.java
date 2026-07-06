package com.CSplashAluguel.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CargoRequest(
        UUID id,
        @NotBlank(message = "Campo Obrigatorio")
        @Size(max=20, message = "No Maximo ate 20 carateres")
        String nomeDoCargo
) {
}

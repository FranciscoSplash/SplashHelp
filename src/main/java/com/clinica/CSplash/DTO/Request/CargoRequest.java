package com.clinica.CSplash.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CargoRequest(
        @NotBlank(message = "Campo Obrigatorio")
        @Size(max=20, message = "No Maximo ate 20 carateres")
        String nomeDoCargo
) {
}

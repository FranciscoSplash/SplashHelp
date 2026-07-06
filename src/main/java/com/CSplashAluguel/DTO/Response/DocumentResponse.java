package com.CSplashAluguel.DTO.Response;

import com.CSplashAluguel.Model.Enum.StatusDoc;

import java.time.LocalDate;
import java.util.UUID;

public record DocumentResponse(
        UUID id,
        String numeroCnh,
        LocalDate validade,
        StatusDoc statusDoc,
        UsuarioResponse usuario
) {
}

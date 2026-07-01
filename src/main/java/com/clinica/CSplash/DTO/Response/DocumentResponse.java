package com.clinica.CSplash.DTO.Response;

import com.clinica.CSplash.Model.Enum.StatusDoc;
import com.clinica.CSplash.Model.Usuario;

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

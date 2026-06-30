package com.clinica.CSplash.DTO.Response;

import com.clinica.CSplash.Model.Cargo;
import com.clinica.CSplash.Model.Enum.StatusUsuario;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String telefone,
        Cargo cargo,
        StatusUsuario statusUsuario
) {
}

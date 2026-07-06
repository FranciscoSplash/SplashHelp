package com.CSplashAluguel.DTO.Response;

import com.CSplashAluguel.Model.Cargo;
import com.CSplashAluguel.Model.Enum.StatusUsuario;

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

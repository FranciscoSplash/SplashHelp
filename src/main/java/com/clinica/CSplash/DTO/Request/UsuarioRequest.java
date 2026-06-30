package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Cargo;
import com.clinica.CSplash.Model.Enum.StatusUsuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record UsuarioRequest(
        String nome,
        String email,
         String senha,
         String telefone,
        Cargo cargo,
        StatusUsuario statusUsuario
) {
}

package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.StatusDoc;
import com.clinica.CSplash.Model.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.UUID;

public record DocumentosRequest(
        @NotBlank(message = "Numero do CNH obrigatorio")
        @Column(unique = true)
        String numeroCnh,

         @NotNull(message = "Data de validade Obrigatorio")

         LocalDate validade,

        @NotNull(message = "Data de validade Obrigatorio")
         StatusDoc statusDoc

) {
}

package com.CSplashAluguel.DTO.Request;

import com.CSplashAluguel.Model.Enum.StatusDoc;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

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

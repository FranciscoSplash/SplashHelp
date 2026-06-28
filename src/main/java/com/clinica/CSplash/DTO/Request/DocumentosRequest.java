package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.StatusDoc;
import com.clinica.CSplash.Model.Usuario;


import java.time.LocalDate;

public record DocumentosRequest(
        String numeroCnh,
         LocalDate validade,
         StatusDoc statusDoc,
         Usuario usuario
) {
}

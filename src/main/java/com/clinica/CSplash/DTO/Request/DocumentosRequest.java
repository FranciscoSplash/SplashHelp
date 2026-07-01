package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.StatusDoc;
import com.clinica.CSplash.Model.Usuario;


import java.time.LocalDate;
import java.util.UUID;

public record DocumentosRequest(
        String numeroCnh,
         LocalDate validade,
         StatusDoc statusDoc

) {
}

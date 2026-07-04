package com.clinica.CSplash.DTO.Request;

import com.clinica.CSplash.Model.Enum.StatusLocacao;

public record RetirarRequest(
        StatusLocacao statusLocacao
) {
}

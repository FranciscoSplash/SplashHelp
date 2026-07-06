package com.CSplashAluguel.DTO.Response;

public record LoginResponse(
        String token,
        UsuarioResponse usuario
) {
}

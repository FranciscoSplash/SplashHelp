package com.CSplashAluguel.DTO.Request;

import com.CSplashAluguel.Model.Enum.StatusUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

public record UsuarioRequest(

        @NotBlank(message = "Campo de nome obrigatorio")
        String nome,

        @NotBlank(message = "Campo de email obrigatorio")
        @Email(message = "Insira o formato de Email valido")
        @Column(unique = true)
        String email,


        @NotBlank(message = "Campo de senha obrigatorio")
        @Size(min = 5, message = "No minimo deve ter 8 carateres")
         String senha,


        @NotBlank(message = "Campo de Telefone obrigatorio")
        @Pattern(
                regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$",
                message = "O telefone deve seguir o padrão (11) 99999-9999."
        )
         String telefone,

        @NotNull(message = "Campo obrigatorio")
        CargoRequest cargo,

        @NotNull(message = "Campo obrigatorio")
        StatusUsuario statusUsuario
) {
}

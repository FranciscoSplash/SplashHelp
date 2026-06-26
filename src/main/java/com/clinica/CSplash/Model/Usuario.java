package com.clinica.CSplash.Model;


import com.clinica.CSplash.Model.Enum.StatusUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;


    private String email;

    private String senha;

    private String telefone;


    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;


    @Enumerated
    private StatusUsuario statusUsuario;
}

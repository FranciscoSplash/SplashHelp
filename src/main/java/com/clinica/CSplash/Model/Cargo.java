package com.clinica.CSplash.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cargo")
public class Cargo {

    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Campo obrigatório")
    private String nomeDoCargo;
}

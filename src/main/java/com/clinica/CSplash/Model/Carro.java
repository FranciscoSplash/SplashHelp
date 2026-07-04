package com.clinica.CSplash.Model;

import com.clinica.CSplash.Model.Enum.StatusCarro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Campo Obrigatorio")
    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @NotBlank(message = "Campo obrigatorio")
    private String marca;

    private String cor;

    private String modelo;

    @NotNull(message = "Campo obrigatorio")
    private Integer ano;

    @NotBlank(message = "Campo obrigatorio")
    @Column(unique = true)
    private String placa;

    @NotNull(message = "Campo obrigatorio")
    private BigDecimal precoDia;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo obrigatorio")
    private StatusCarro statusCarro;



}

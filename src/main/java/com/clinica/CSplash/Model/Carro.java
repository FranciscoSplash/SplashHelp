package com.clinica.CSplash.Model;

import com.clinica.CSplash.Model.Enum.StatusCarro;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    private String marca;

    private String cor;

    private String modelo;

    private Integer ano;

    private String placa;

    private BigDecimal precoDia;

    private StatusCarro statusCarro;

}

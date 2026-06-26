package com.clinica.CSplash.Model;



import com.clinica.CSplash.Model.Enum.StatusLocacao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "locacao")


public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private StatusLocacao statusLocacao;
}

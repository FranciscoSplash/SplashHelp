package com.CSplashAluguel.Model;


import com.CSplashAluguel.Model.Enum.StatusDoc;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "documentos")
public class Documentos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String numeroCnh;
    private LocalDate validade;

    @Enumerated(EnumType.STRING)
    private StatusDoc statusDoc;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}

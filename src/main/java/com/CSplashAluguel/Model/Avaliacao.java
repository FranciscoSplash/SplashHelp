package com.CSplashAluguel.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


            @ManyToOne
            @JoinColumn(name = "locacao_id")
            private Locacao locacao;

            @ManyToOne
            @JoinColumn(name = "usuario_id")
            private Usuario Usuario;


            private Integer nota;

            private String comentario;

             @CreationTimestamp
             private LocalDateTime criadoEm;
}

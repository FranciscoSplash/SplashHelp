package com.CSplashAluguel.Model;


import com.CSplashAluguel.Model.Enum.MetodoPagamento;
import com.CSplashAluguel.Model.Enum.StatusPagamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name="locacao_id")
    private Locacao locacao;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private StatusPagamento statusPagamento;
}

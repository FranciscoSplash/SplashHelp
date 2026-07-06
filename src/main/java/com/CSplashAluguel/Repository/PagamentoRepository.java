package com.CSplashAluguel.Repository;


import com.CSplashAluguel.Model.Locacao;
import com.CSplashAluguel.Model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
    Optional<Pagamento>findByLocacao(Locacao local);

}

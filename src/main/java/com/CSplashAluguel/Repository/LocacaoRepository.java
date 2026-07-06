package com.CSplashAluguel.Repository;

import com.CSplashAluguel.Model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, UUID> {





}

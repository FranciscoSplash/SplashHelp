package com.CSplashAluguel.Repository;

import com.CSplashAluguel.Model.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentosRepository extends JpaRepository<Documentos, UUID> {
    boolean existsByNumeroCnh(String numeroCnh);

    }

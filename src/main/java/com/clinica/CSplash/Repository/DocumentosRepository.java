package com.clinica.CSplash.Repository;

import com.clinica.CSplash.Model.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentosRepository extends JpaRepository<Documentos, UUID> {
}

package com.clinica.CSplash.Repository;

import com.clinica.CSplash.Model.Carro;
import com.clinica.CSplash.Model.Enum.StatusCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

    //verifica se a placa do carro ja existe na base de dados
    boolean existsByPlaca(String placa);

    }

package com.CSplashAluguel.Repository;

import com.CSplashAluguel.Model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

    //verifica se a placa do carro ja existe na base de dados
    boolean existsByPlaca(String placa);

    }

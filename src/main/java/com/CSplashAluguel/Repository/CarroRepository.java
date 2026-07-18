package com.CSplashAluguel.Repository;

import com.CSplashAluguel.Model.Carro;
import com.CSplashAluguel.Model.Enum.StatusCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarroRepository extends JpaRepository<Carro, UUID> {

    //verifica se a placa do carro ja existe na base de dados
    boolean existsByPlaca(String placa);

    //verifica a disponibilidade do carro
    List<Carro> findByStatusCarro(StatusCarro statusCarro);

    }

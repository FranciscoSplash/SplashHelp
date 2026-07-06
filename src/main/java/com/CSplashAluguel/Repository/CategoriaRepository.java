package com.CSplashAluguel.Repository;


import com.CSplashAluguel.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, UUID > {
    boolean existsByNomeDaCategoria(String nomeDaCategoria);
}

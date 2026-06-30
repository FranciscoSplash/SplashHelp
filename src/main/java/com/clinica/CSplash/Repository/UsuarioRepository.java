package com.clinica.CSplash.Repository;

import com.clinica.CSplash.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {


}

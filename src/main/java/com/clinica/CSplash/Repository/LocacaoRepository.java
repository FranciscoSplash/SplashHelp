package com.clinica.CSplash.Repository;

import com.clinica.CSplash.Model.Enum.StatusDoc;
import com.clinica.CSplash.Model.Enum.StatusUsuario;
import com.clinica.CSplash.Model.Locacao;
import com.clinica.CSplash.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, UUID> {

    //verificar se usuario esta ativo
    boolean usuarioActivo(StatusUsuario statusUsuario);



}

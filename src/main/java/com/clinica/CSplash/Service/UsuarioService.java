package com.clinica.CSplash.Service;

import com.clinica.CSplash.DTO.Request.UsuarioRequest;
import com.clinica.CSplash.DTO.Response.UsuarioResponse;
import com.clinica.CSplash.Model.Cargo;
import com.clinica.CSplash.Model.Usuario;
import com.clinica.CSplash.Repository.CargoRepository;
import com.clinica.CSplash.Repository.CarroRepository;
import com.clinica.CSplash.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;


    public List<UsuarioResponse> listarUsuario(){
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UsuarioResponse listarPorId(UUID id){
        Usuario user=usuarioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id inexistente"));

        return toResponse(user);
    }
    public UsuarioResponse criarUsuario(UsuarioRequest request){

        Cargo cargo=cargoRepository.findById(request.cargo().getId())
                .orElseThrow(()->new RuntimeException("ID Inexistente"));

        Usuario user=new Usuario();
        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setSenha(request.senha());
        user.setTelefone(request.telefone());
        user.setCargo(cargo);
        user.setStatusUsuario(request.statusUsuario());

        return toResponse(usuarioRepository.save(user));

    }
    public UsuarioResponse atualizarUsuario(UUID id, UsuarioRequest request){
        Usuario user=usuarioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id inexistente"));

        Cargo cargo=cargoRepository.findById(request.cargo().getId())
                .orElseThrow(()->new RuntimeException("ID Inexistente"));

        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setSenha(request.senha());
        user.setTelefone(request.telefone());
        user.setCargo(cargo);
        user.setStatusUsuario(request.statusUsuario());


        return toResponse(usuarioRepository.save(user));

    }

    public void apagarUsuario(UUID id){

        Usuario user=usuarioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Id inexistente"));

        usuarioRepository.delete(user);
    }

    public UsuarioResponse toResponse(Usuario user){
        return  new UsuarioResponse(
        user.getId(),
        user.getNome(),
        user.getEmail(),
        user.getTelefone(),
        user.getCargo(),
        user.getStatusUsuario()
        );
    }
}

package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Request.CategoriaRequest;
import com.CSplashAluguel.DTO.Response.CategoriaResponse;
import com.CSplashAluguel.Model.Categoria;
import com.CSplashAluguel.Repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaResponse criarCategoria(CategoriaRequest request){


        if(categoriaRepository.existsByNomeDaCategoria(request.nomeDaCategoria())){
            throw new IllegalArgumentException("Nome da Categoria ja existente");
        }

        Categoria categoria=new Categoria();
        categoria.setNomeDaCategoria(request.nomeDaCategoria());
        categoria.setDescricao(request.descricao());


        return toResponse(categoriaRepository.save(categoria));
    }
    public CategoriaResponse atualizarCategoria(UUID id, CategoriaRequest categoriaRequest){
        Categoria categoria=categoriaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Id inexistente"));

        categoria.setNomeDaCategoria(categoriaRequest.nomeDaCategoria());
        categoria.setDescricao(categoriaRequest.descricao());

        return toResponse(categoriaRepository.save(categoria));
    }
    public List<CategoriaResponse> listarCategoria(){
        return categoriaRepository.findAll().stream().map(this::toResponse).toList();
    }
    public CategoriaResponse listarPorId(UUID id){
        Categoria categoria=categoriaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Id inexistente"));

        return toResponse(categoria);
    }

    public void apagar(UUID id){
       Categoria categoria= categoriaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Id inexistente"));

       categoriaRepository.delete(categoria);
    }
    public CategoriaResponse toResponse(Categoria categoria){
        return  new CategoriaResponse(
                categoria.getId(),
                categoria.getNomeDaCategoria(),
                categoria.getDescricao()
        );
    }
}

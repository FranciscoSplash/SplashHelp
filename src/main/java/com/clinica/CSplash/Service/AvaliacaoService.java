package com.clinica.CSplash.Service;


import com.clinica.CSplash.DTO.Request.AvalicaoRequest;
import com.clinica.CSplash.DTO.Response.AvaliacaoResponse;
import com.clinica.CSplash.Model.Avaliacao;
import com.clinica.CSplash.Model.Locacao;
import com.clinica.CSplash.Repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoResponse criarAvalicao(AvalicaoRequest avalicaoRequest){
        Avaliacao avaliacao=new Avaliacao();

        avalicaoRequest.locacaoId();
        avaliacao.setNota(avalicaoRequest.nota());
        avaliacao.setComentario(avalicaoRequest.comentario());

        return toResponse (avaliacaoRepository.save(avaliacao));
    }

    public List<AvaliacaoResponse> listarAvaliacao(){
        return avaliacaoRepository.findAll().stream().map(this::toResponse).toList();
    }
    public AvaliacaoResponse listarAvaliacaoPorId(UUID id){
        Avaliacao avaliacao=avaliacaoRepository.findById(id).orElseThrow(()->new RuntimeException("ID não encontrado"));

        return toResponse(avaliacao);
    }

    public void  apagarAvaliacao(UUID id){
        Avaliacao avaliacao=avaliacaoRepository.findById(id).orElseThrow(()->new RuntimeException("ID não encontrado"));

       avaliacaoRepository.delete(avaliacao);
    }
    public AvaliacaoResponse toResponse(Avaliacao avaliacao){
        return new AvaliacaoResponse(
                avaliacao.getId(),
                avaliacao.getLocacao(),
                avaliacao.getUsuario(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getCriadoEm()



        );
    }
}

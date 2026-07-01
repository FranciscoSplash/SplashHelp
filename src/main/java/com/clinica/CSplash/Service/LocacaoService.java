package com.clinica.CSplash.Service;

import com.clinica.CSplash.DTO.Request.LocacaoRequest;
import com.clinica.CSplash.DTO.Response.LocacaoResponse;
import com.clinica.CSplash.DTO.Response.UsuarioResponse;
import com.clinica.CSplash.Model.*;
import com.clinica.CSplash.Model.Enum.StatusCarro;
import com.clinica.CSplash.Model.Enum.StatusLocacao;
import com.clinica.CSplash.Model.Enum.StatusPagamento;
import com.clinica.CSplash.Model.Enum.StatusUsuario;
import com.clinica.CSplash.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private DocumentosRepository documentosRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public LocacaoResponse criarLocacao(LocacaoRequest request) {

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        Carro carro = carroRepository.findById(request.carroId())
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        if (usuario.getStatusUsuario() != StatusUsuario.ATIVO) {
            throw new IllegalArgumentException("O usuário precisa estar ativo para alugar");
        }

        System.out.println(carro.getStatusCarro());
        if (carro.getStatusCarro() != StatusCarro.LIVRE) {
            throw new IllegalArgumentException("Este carro já está ocupado ou em manutenção.");
        }

        if (request.dataInicio().isAfter(request.dataFim())) {
            throw new IllegalArgumentException("Error! Tem atencão ao horario");
        }

        double tempo = Duration.between(request.dataInicio(), request.dataFim()).toHours();

        BigDecimal resultado = carro.getPrecoDia().multiply(BigDecimal.valueOf(tempo));


        Locacao locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setCarro(carro);
        locacao.setDataInicio(request.dataInicio());
        locacao.setDataFim(request.dataFim());
        locacao.setPreco(resultado);
        locacao.setStatusLocacao(StatusLocacao.PENDENTE);

        carro.setStatusCarro(StatusCarro.LIVRE);
        carroRepository.save(carro);

        return toResponse(locacaoRepository.save(locacao));
    }
    public LocacaoResponse atualizarlocacao(UUID id, LocacaoRequest request){
        Usuario usuario=usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        Carro carro = carroRepository.findById(request.carroId())
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        Locacao locacao=locacaoRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        locacao.setUsuario(usuario);
        locacao.setCarro(carro);
        locacao.setPreco(request.preco());
        locacao.setDataInicio(request.dataInicio());
        locacao.setDataFim(request.dataFim());
        locacao.setStatusLocacao(request.statusLocacao());

        return  toResponse(locacaoRepository.save(locacao));
    }
    public LocacaoResponse confirmarRetirada(UUID id, LocacaoRequest request){
        Locacao local =locacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Locacão não encontrado"));

     Pagamento pagamento=pagamentoRepository.findByLocacao(local)
             .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));

       Carro carro=local.getCarro();


        if(pagamento.getStatusPagamento()!=StatusPagamento.PAGO){
           throw new RuntimeException("Ainda nao pago");
        }


        carro.setStatusCarro(StatusCarro.ALUGADO);
        carroRepository.save(carro);
        local.setStatusLocacao(StatusLocacao.ATIVO);

         return toResponse(locacaoRepository.save(local));

    }
    public LocacaoResponse concluirDevolucao(UUID id){
        Locacao locacao=locacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        locacao.setStatusLocacao(StatusLocacao.CONCLUIDO);

        Carro carro=locacao.getCarro();
        carro.setStatusCarro(StatusCarro.LIVRE);
        carroRepository.save(carro);

        return toResponse(locacaoRepository.save(locacao));
    }
public LocacaoResponse cancelarLocacao(UUID id){
    Carro carro=carroRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

    carro.setStatusCarro(StatusCarro.LIVRE);
    carroRepository.save(carro);

    Locacao locacao=locacaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

    locacao.setStatusLocacao(StatusLocacao.CANCELADO);

    return toResponse(locacaoRepository.save(locacao));
}
    public List<LocacaoResponse> listarLocacao(){
        return locacaoRepository.findAll().stream().map(this::toResponse).toList();
    }
    public LocacaoResponse listarPoId(UUID id){
        Locacao locacao=locacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        return toResponse(locacao);
    }
    public void apagarLocacao(UUID id){
        Locacao locacao=locacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id Inexistente"));

        locacaoRepository.delete(locacao);
    }

    public LocacaoResponse toResponse(Locacao locacao){
        return new LocacaoResponse(
                locacao.getId(),
                locacao.getDataInicio(),
                locacao.getDataFim(),
                locacao.getPreco(),
                new UsuarioResponse(
                        locacao.getUsuario().getId(),
                        locacao.getUsuario().getNome(),
                        locacao.getUsuario().getEmail(),
                        locacao.getUsuario().getTelefone(),
                        locacao.getUsuario().getCargo(),
                        locacao.getUsuario().getStatusUsuario()
                ),
                locacao.getCarro(),
                locacao.getStatusLocacao()
        );
    }
}

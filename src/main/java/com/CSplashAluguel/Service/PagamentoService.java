package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Request.PagamentoRequest;
import com.CSplashAluguel.DTO.Response.PagamentoResponse;
import com.CSplashAluguel.Model.Enum.MetodoPagamento;
import com.CSplashAluguel.Model.Enum.StatusLocacao;
import com.CSplashAluguel.Model.Enum.StatusPagamento;
import com.CSplashAluguel.Model.Locacao;
import com.CSplashAluguel.Model.Pagamento;
import com.CSplashAluguel.Repository.LocacaoRepository;
import com.CSplashAluguel.Repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    public PagamentoResponse gerarIntencaoPagamento(PagamentoRequest request){

        // 1. Busca a locação para a qual o cliente está tentando pagar
        // (Assumindo que seu request traz o id da locação: request.locacaoId())
        Locacao locacao=locacaoRepository.findById(request.locacaoId())
                .orElseThrow(()->new EntityNotFoundException("Locacao não encontrada"));

        Pagamento pagamento=new Pagamento();
        pagamento.setLocacao(locacao);
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        pagamento.setMetodoPagamento(MetodoPagamento.PIX);

        return toResponse(pagamentoRepository.save(pagamento));
    }
    public PagamentoResponse confirmarPagamento(UUID id, PagamentoRequest request){
        Pagamento pagamento=pagamentoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Id Inexistente"));

        Locacao locacao=pagamento.getLocacao();

        if(pagamento.getStatusPagamento()==StatusPagamento.PAGO){
            throw new RuntimeException("ja foi processado e pago");
        }
        pagamento.setStatusPagamento(StatusPagamento.PAGO);
        locacao.setStatusLocacao(StatusLocacao.CONFIRMADO);
        locacaoRepository.save(locacao);

        return toResponse(pagamentoRepository.save(pagamento));
    }

    public List<PagamentoResponse> listarTodosPagamentos(){
        return pagamentoRepository.findAll().stream().map(this::toResponse).toList();

    }

    public PagamentoResponse toResponse(Pagamento pagamento){
        return new PagamentoResponse(
                pagamento.getId(),
                pagamento.getLocacao(),
                pagamento.getMetodoPagamento(),
                pagamento.getStatusPagamento()
        );
    }
}

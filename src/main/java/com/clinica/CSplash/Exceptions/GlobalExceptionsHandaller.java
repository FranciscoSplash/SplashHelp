package com.clinica.CSplash.Exceptions;


import com.clinica.CSplash.Config.DadosValidacao;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionsHandaller {


    //Trata o Erro 404 (Quando o ID do banco não existe)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DadosValidacao> tratarError404(EntityNotFoundException e) {

        DadosValidacao validacao = new DadosValidacao(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso Nao encontrado",
                e.getMessage()


        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(validacao);

    }

    //regras de negócio manuais
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DadosValidacao> tratarRegrasDoNegocio(IllegalArgumentException e) {

        DadosValidacao validacao = new DadosValidacao(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Regra Violada",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validacao);

    }
    // QUALQUER outro erro bizarro que quebre o Java
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosValidacao> tratarErroGerl500(Exception e) {

        DadosValidacao validacao = new DadosValidacao(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "ERRO NO SERVIDOR",
                "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde."

        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validacao);

    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<java.util.Map<String,String>>tratarValidacao(org.springframework.web.bind.MethodArgumentNotValidException e){
        java.util.Map<String, String> erro= new java.util.HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            erro.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erro);
    }
    }




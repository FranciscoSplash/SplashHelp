package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Request.CategoriaRequest;
import com.CSplashAluguel.DTO.Response.CategoriaResponse;
import com.CSplashAluguel.Model.Categoria;
import com.CSplashAluguel.Repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Nested
    class Cadastrar {


        @Test
        void criarCategoria() {

            CategoriaRequest request=new CategoriaRequest(
                    "economico",
                    "Carro simples com Ac e conforto"
            );

            Categoria categoria = new Categoria();
            categoria.setId(UUID.randomUUID());
            categoria.setNomeDaCategoria("economico");
            categoria.setDescricao("Carro simples com Ac e conforto");

            when(categoriaRepository.existsByNomeDaCategoria(anyString())).thenReturn(false);

            when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

            CategoriaResponse response =categoriaService.criarCategoria(request);

            assertNotNull(response);
            assertEquals(request.nomeDaCategoria(), response.nomeDaCategoria());
            assertEquals(request.descricao(), response.descricao());
        }
    }
    @Nested
    class ErrorCadastrar{
        @Test
        void errorCadastrar(){

            CategoriaRequest request= new CategoriaRequest(
                    "economico",
                    "Carro simples com Ac e conforto"
            );


            when(categoriaRepository.existsByNomeDaCategoria(anyString())).thenThrow(new RuntimeException("Categoria ja existe"));

          RuntimeException exception= assertThrows( RuntimeException.class,() -> categoriaService.criarCategoria(request));

            assertEquals("Categoria ja existe", exception.getMessage());
        }
    }
    @Nested
    class  AtualizarCategoria{
    @Test
    void atualizarCategoria() {

        UUID id =UUID.randomUUID();
        CategoriaRequest request =new CategoriaRequest(
                "economico",
                "Carro simples com Ac e conforto"

        );
        Categoria categoria=new Categoria();
        categoria.setNomeDaCategoria("executivo");
        categoria.setDescricao("Carro simples com Ac e conforto");

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        var response = categoriaService.atualizarCategoria(id, request);

        assertEquals(request.nomeDaCategoria(),response.nomeDaCategoria());
        assertEquals(request.descricao(), response.descricao());
    }
    }

    @Nested
    class ErroAtualizar{
        @Test
        void erroratualizar(){

            UUID id = UUID.randomUUID();
            CategoriaRequest request =new CategoriaRequest(
                    "economico",
                    "Carro simples com Ac e conforto"
            );
            when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

            EntityNotFoundException exception= assertThrows( EntityNotFoundException.class,() -> categoriaService.atualizarCategoria(id, request));

            assertEquals("Id inexistente", exception.getMessage());
        }
    }

    @Nested
    class ListarCategorias{

        @Test
    void listarCategoria() {

            Categoria categoria=new Categoria();
            when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

            var response =categoriaService.listarCategoria();

            assertEquals(1, response.size());
    }
    }
    @Nested
    class  ErrorListar{
        @Test
        void errorLista(){


            when(categoriaRepository.findAll()).thenThrow(new RuntimeException("Lista vazia"));

            RuntimeException exception= assertThrows( RuntimeException.class,() -> categoriaService.listarCategoria());

            assertEquals("Lista vazia", exception.getMessage());
        }
    }
    @Test
    void listarPorId() {
        UUID id =UUID.randomUUID();

        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        var response =categoriaService.listarPorId(id);

        assertEquals(categoria.getNomeDaCategoria(), response.nomeDaCategoria());
        assertEquals(categoria.getDescricao(), response.descricao());
    }
    @Nested
    class ErrorListarporId{
        @Test
        void errorPorId(){

            UUID id = UUID.randomUUID();

            when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

            EntityNotFoundException exception= assertThrows( EntityNotFoundException.class,() -> categoriaService.listarPorId(id));

            assertEquals("Id inexistente", exception.getMessage());
        }
    }

    @Test
    void apagar() {
        UUID id = UUID.randomUUID();
        Categoria categoria = new Categoria();

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));


         categoriaService.apagar(id);

    }
    @Test
    void errorApagar(){

        UUID id = UUID.randomUUID();

        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception= assertThrows( EntityNotFoundException.class,() -> categoriaService.apagar(id));

        assertEquals("Id inexistente", exception.getMessage());
    }


}
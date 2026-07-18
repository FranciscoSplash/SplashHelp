package com.CSplashAluguel.Service;

import com.CSplashAluguel.DTO.Request.CarroRequest;
import com.CSplashAluguel.DTO.Response.CarroResponse;
import com.CSplashAluguel.DTO.Response.MapsResponse;
import com.CSplashAluguel.Model.Categoria;
import com.CSplashAluguel.Model.Enum.StatusCarro;
import com.CSplashAluguel.Repository.CarroRepository;
import com.CSplashAluguel.Model.Carro;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @Mock
    private CarroRepository carroRepository;

    @Mock
    private MapsService mapsService;

    @InjectMocks
    private  CarroService carroService;

    @Captor
    private ArgumentCaptor <Carro> argumentCaptor;

    @Captor
    private ArgumentCaptor <UUID> uuidArgumentCaptor;

    @Captor
    private ArgumentCaptor <List> listArgumentCaptor;
    @Nested
    class Cadastrar{

        @Test
        @DisplayName("Deve montar corretamente a entidade Carro")
        void deveMontarEntidadeCarro() throws IOException {

            Categoria categoria= new Categoria();
               categoria.setId(UUID.randomUUID());

               categoria.setNomeDaCategoria("suv");
            CarroRequest request = new CarroRequest(

                    categoria,
                    "Ferrari",
                    "preta",
                    "F12020",
                    2026,
                    "F2AY",
                    new BigDecimal("300"),
                    "Rua Speers",
                    StatusCarro.LIVRE
            );

            MultipartFile file = new MockMultipartFile(
                    "imagem",
                    "ferrari.jpg",
                    "image/jpg",
                    "imagem".getBytes()
            );

            MapsResponse mapsResponse = new MapsResponse(
                    -23.550520,
                    -46.633308
            );


            Carro carroSalvo = new Carro();
            carroSalvo.setId(UUID.randomUUID());
            carroSalvo.setCategoria(categoria);
            carroSalvo.setMarca("Ferrari");
            carroSalvo.setCor("Preta");
            carroSalvo.setModelo("F12020");
            carroSalvo.setAno(2026);
            carroSalvo.setPlaca("F2AY");
            carroSalvo.setEndereco("Rua Speers");
            carroSalvo.setPrecoDia(new BigDecimal("300"));
            carroSalvo.setStatusCarro(StatusCarro.LIVRE);

            doReturn(false).when(carroRepository).existsByPlaca(any());

            when(mapsService.buscar(anyString()))
                    .thenReturn(mapsResponse);

            when(carroRepository.save(any(Carro.class))).thenReturn(carroSalvo);



            CarroResponse response = carroService.cadastrarCarro(request, file);
            //verify

            verify(carroRepository).save(argumentCaptor.capture());

            var carroCaptured = argumentCaptor.getValue();


            //asert

            assertNotNull(request);
            assertEquals(request.marca(), carroCaptured.getMarca());
            assertEquals(request.cor(),carroCaptured.getCor());
            assertEquals(request.modelo(),carroCaptured.getModelo());
            assertEquals(request.ano(),carroCaptured.getAno());
            assertEquals(request.placa(),carroCaptured.getPlaca());
            assertEquals(request.preco(),carroCaptured.getPrecoDia());
            assertEquals(request.statusCarro(), carroCaptured.getStatusCarro());

            //response

            // Assert
            assertNotNull(response);
            assertEquals(carroSalvo.getMarca(), response.marca());
            assertEquals(carroSalvo.getCor(), response.cor());
            assertEquals(carroSalvo.getModelo(), response.modelo());
            assertEquals(carroSalvo.getAno(), response.ano());
            assertEquals(carroSalvo.getPlaca(), response.placa());
            assertEquals(carroSalvo.getPrecoDia(), response.preco());
            assertEquals(carroSalvo.getStatusCarro(), response.statusCarro());


        }

        @Test
        @DisplayName("mostrando erro")
        void mostrarErroThrowException(){
            Categoria categoria= new Categoria();
            categoria.setId(UUID.randomUUID());

            categoria.setNomeDaCategoria("suv");
            CarroRequest request = new CarroRequest(

                    categoria,
                    "Ferrari",
                    "preta",
                    "F12020",
                    2026,
                    "F2AY",
                    new BigDecimal("300"),
                    "Rua Speers",
                    StatusCarro.LIVRE
            );

            MultipartFile file = new MockMultipartFile(
                    "imagem",
                    "ferrari.jpg",
                    "image/jpg",
                    "imagem".getBytes()
            );

            MapsResponse mapsResponse = new MapsResponse(
                    -23.550520,
                    -46.633308
            );




            when(carroRepository.existsByPlaca(anyString()))
                    .thenThrow(new RuntimeException("Erro de teste"));

            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> carroService.cadastrarCarro(request, file)
            );

            assertEquals("Erro de teste", exception.getMessage());
        }
    }
    @Nested
    class Atualizar{
        @Test
        @DisplayName("atualizar carro")
        void mostrarAtualiacao(){

            UUID id = UUID.randomUUID();
            Categoria categoria = new Categoria();
            categoria.setNomeDaCategoria("Economico");

            CarroRequest request = new CarroRequest(

                    categoria,
                    "BMW",
                    "preta",
                    "X6",
                    2026,
                    "ABC1D23",
                    new BigDecimal("300"),
                    "Rua Speers",
                    StatusCarro.LIVRE
            );

            Carro carro = new Carro();
            carro.setCategoria(categoria);
            carro.setMarca("Ferrari");
            carro.setCor("Preta");
            carro.setModelo("F8 Spider");
            carro.setAno(2026);
            carro.setPlaca("ABC1D23");
            carro.setEndereco("Rua Speers");
            carro.setPrecoDia(new BigDecimal("300"));
            carro.setStatusCarro(StatusCarro.LIVRE);


            when(carroRepository.findById(id))
                    .thenReturn(Optional.of(carro));

            when(carroRepository.save(any(Carro.class)))
                    .thenReturn(carro);

            var response = carroService.atualizarCarro(id, request);

            assertEquals("BMW", response.marca());
            assertEquals("X6", response.modelo());
            verify(carroRepository).save(any(Carro.class));
        }
    }

    @Nested
    class AtualizarErros{
        @Test
        void mostarAtualizarErros(){
            UUID id= UUID.randomUUID();
            Categoria categoria=new Categoria();

            CarroRequest request= new CarroRequest(
                    categoria,
                    "BMW",
                    "preta",
                    "X6",
                    2026,
                    "ABC1D23",
                    new BigDecimal("300"),
                    "Rua Speers",
                    StatusCarro.LIVRE
            );

            when(carroRepository.findById(id)).thenReturn(Optional.empty());

            EntityNotFoundException exception= assertThrows(EntityNotFoundException.class,()
                    ->carroService.atualizarCarro(id, request));

            assertEquals("Id inexistente", exception.getMessage());
        }
    }
    @Nested
    class ListarCarros{

        @Test
        void listarCarros(){
            Categoria categoria = new Categoria();
            categoria.setNomeDaCategoria("Economico");

            Carro carro = new Carro();
            carro.setCategoria(categoria);
            carro.setMarca("Ferrari");
            carro.setCor("Preta");
            carro.setModelo("F8 Spider");
            carro.setAno(2026);
            carro.setPlaca("ABC1D23");
            carro.setEndereco("Rua Speers");
            carro.setPrecoDia(new BigDecimal("300"));
            carro.setStatusCarro(StatusCarro.LIVRE);


            doReturn(List.of(carro)).when(carroRepository).findAll(any(Sort.class));



            var response = carroService.listarCarros();

            verify(carroRepository).findAll(any(Sort.class));

            assertEquals(1, response.size());
            assertEquals("Ferrari", response.get(0).marca());
            assertEquals("F8 Spider", response.get(0).modelo());
            assertEquals("Preta", response.get(0).cor());
        }
    }
    @Nested
    class ListarErros{
        @Test
        void mostrarErro(){

            when(carroRepository.findAll(any(Sort.class)))
                    .thenThrow(new RuntimeException("Lista vazia" ));

            RuntimeException Exception =assertThrows(RuntimeException.class,()
            -> carroService.listarCarros());

            assertEquals("Lista vazia", Exception.getMessage());

        }
    }
    @Nested
    class ListarPorId{

        @Test
        @DisplayName("listar por id")
        void mostrarPorId(){

            UUID id = UUID.randomUUID();
            Categoria categoria=new Categoria();

            categoria.setNomeDaCategoria("suv");
           Carro carro=new Carro();

           carro.setId(id);
           carro.setCategoria(categoria);
            carro.setMarca("Ferrari");
            carro.setCor("Preta");
            carro.setModelo("F8 Spider");
            carro.setAno(2026);
            carro.setPlaca("ABC1D23");
            carro.setEndereco("Rua Speers");
            carro.setPrecoDia(new BigDecimal("300"));
            carro.setStatusCarro(StatusCarro.LIVRE);

           when(carroRepository.findById(id)).thenReturn(Optional.of(carro));


            CarroResponse response= carroService.listarPorId(id);
            verify(carroRepository).findById(id);


            assertNotNull(response);
            assertEquals(carro.getId(), response.id());
            assertEquals(carro.getMarca(), response.marca());
            assertEquals(carro.getCor(), response.cor());
            assertEquals(carro.getModelo(), response.modelo());
            assertEquals(carro.getAno(), response.ano());
            assertEquals(carro.getPlaca(), response.placa());
            assertEquals(carro.getPrecoDia(), response.preco());
            assertEquals(carro.getStatusCarro(), response.statusCarro());


        }
    }
@Nested
class ListarPorIdErro{
        @Test void
        mostrarErro(){

            UUID id = UUID.randomUUID();

            when(carroRepository.findById(id)).thenReturn(Optional.empty());

            EntityNotFoundException Exception = assertThrows(EntityNotFoundException.class,()->
                    carroService.listarPorId(id));

            assertEquals("Id não Encontrado", Exception.getMessage());
    }
}
    @Nested
    class Apagar{
        @Test
        @DisplayName("Apagar")
        void mostarApagos(){

            UUID id=UUID.randomUUID();
            carroService.apgarCarro(id);


            verify(carroRepository).deleteById(id);




            assertEquals(id, uuidArgumentCaptor.getValue());
        }
    }


    @Nested
    class DeletarPorIdErro{
        @Test void
        mostrarApagosErro(){

            UUID id = UUID.randomUUID();

            when(carroRepository.findById(id)).thenReturn(Optional.empty());

            EntityNotFoundException Exception = assertThrows(EntityNotFoundException.class,()->
                    carroService.apgarCarro(id));

            assertEquals("Id inexistente", Exception.getMessage());
        }
    }

}
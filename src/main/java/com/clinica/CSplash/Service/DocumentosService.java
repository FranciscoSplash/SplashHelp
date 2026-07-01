package com.clinica.CSplash.Service;

import com.clinica.CSplash.DTO.Request.DocumentosRequest;
import com.clinica.CSplash.DTO.Response.DocumentResponse;
import com.clinica.CSplash.DTO.Response.UsuarioResponse;
import com.clinica.CSplash.Model.Documentos;
import com.clinica.CSplash.Model.Enum.StatusDoc;
import com.clinica.CSplash.Model.Usuario;
import com.clinica.CSplash.Repository.DocumentosRepository;
import com.clinica.CSplash.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentosService {
    @Autowired
    private DocumentosRepository documentosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DocumentResponse criarDocumento(DocumentosRequest request){

        if(documentosRepository.existsByNumeroCnh(request.numeroCnh())){
            throw new IllegalArgumentException("CNH ja cadastrado");
        }

        Documentos doc= new Documentos();
        doc.setNumeroCnh(request.numeroCnh());
        doc.setStatusDoc(StatusDoc.PENDENTE);
        doc.setValidade(request.validade());



        return toResponse(documentosRepository.save(doc));
    }
    public DocumentResponse atualizarDocumento(UUID id, DocumentosRequest documentosRequest){
        Documentos doc=documentosRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Documento não encontrado"));

        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuario não encontrado"));

        doc.setNumeroCnh(documentosRequest.numeroCnh());
        doc.setStatusDoc(documentosRequest.statusDoc());
        doc.setValidade(documentosRequest.validade());

        return toResponse(documentosRepository.save(doc));
    }
    public List<DocumentResponse>listar(){
        return documentosRepository.findAll().stream().map(this::toResponse).toList();
    }
    public DocumentResponse litarPorId(UUID id){
        Documentos doc=documentosRepository.findById(id).orElseThrow(()->new EntityNotFoundException("ID inexistente"));

        return toResponse(doc);
    }
    public  void apagar(UUID id){
        Documentos doc= documentosRepository.findById(id).orElseThrow(()->new EntityNotFoundException("ID inexistente"));

        documentosRepository.delete(doc);

    }

    public DocumentResponse toResponse(Documentos doc){
        return new DocumentResponse(
                doc.getId(),
                doc.getNumeroCnh(),
                doc.getValidade(),
                doc.getStatusDoc(),
                new UsuarioResponse(
                        doc.getUsuario().getId(),
                        doc.getUsuario().getNome(),
                        doc.getUsuario().getEmail(),
                        doc.getUsuario().getTelefone(),
                        doc.getUsuario().getCargo(),
                        doc.getUsuario().getStatusUsuario()
                )
        );
    }
}

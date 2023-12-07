package com.iftm.projetofinalnosql.services;

import com.iftm.projetofinalnosql.models.dtos.ImovelDTO;
import com.iftm.projetofinalnosql.repositories.ImovelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository repository;

    public ResponseEntity<List<ImovelDTO>> findAll() {
        var imovels = repository.findAll();
        if(imovels.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(imovels.stream().map(ImovelDTO::new).collect(Collectors.toList()));
    }

    public ResponseEntity<ImovelDTO> findById(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();
        return repository.findById(id).map(value ->
                ResponseEntity.ok(new ImovelDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ImovelDTO> save(ImovelDTO imovelDTO) {
        if(imovelDTO.getTipo_imovel().isBlank()
                || imovelDTO.getEndereco().isBlank()
                || imovelDTO.getArea() == null
                || imovelDTO.getPreco() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new ImovelDTO(repository.save(imovelDTO.toImovel())));
    }

    public ResponseEntity<ImovelDTO> update(ImovelDTO imovelDTO) {
        if(imovelDTO.getId() == null)
            return ResponseEntity.badRequest().build();

        var dbImovel = repository.findById(new ObjectId(imovelDTO.getId()));
        if(dbImovel.isEmpty())
            return ResponseEntity.notFound().build();

        var dbImovelObj = dbImovel.get();
        dbImovelObj.setTipo_imovel(imovelDTO.getTipo_imovel());
        dbImovelObj.setArea(imovelDTO.getArea());
        dbImovelObj.setEndereco(imovelDTO.getEndereco());
        dbImovelObj.setPreco(imovelDTO.getPreco());
        return ResponseEntity.ok(new ImovelDTO(repository.save(dbImovelObj)));
    }

    public ResponseEntity<?> delete(ObjectId id) {
        if(id == null)
            return ResponseEntity.badRequest().build();

        repository.deleteById(id);

        if(repository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

        return ResponseEntity.ok().build();
    }
}

package com.iftm.projetofinalnosql.controllers;

import com.iftm.projetofinalnosql.models.dtos.ImovelDTO;
import com.iftm.projetofinalnosql.services.ImovelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/imovel")
public class ImovelController {

    @Autowired
    private ImovelService service;

    @GetMapping
    public ResponseEntity<List<ImovelDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(ImovelDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ImovelDTO> findById(@PathVariable("id") ObjectId id) {
        return  service.findById(id).map(imovel ->
                ResponseEntity.ok(new ImovelDTO(imovel))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ImovelDTO> create(@RequestBody ImovelDTO employeeDTO) {
        return ResponseEntity.ok(new ImovelDTO(service.save(employeeDTO.toImovel())));
    }

    @PutMapping
    public ResponseEntity<ImovelDTO> update(@RequestBody ImovelDTO imovelDTO) {
        if(imovelDTO.getId() != null) {
            service.findById(new ObjectId(imovelDTO.getId())).map(imovel ->
                    ResponseEntity.ok(new ImovelDTO(service.update(imovelDTO, imovel))))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}

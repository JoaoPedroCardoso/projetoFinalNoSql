package com.iftm.projetofinalnosql.controllers;

import com.iftm.projetofinalnosql.models.dtos.ImovelDTO;
import com.iftm.projetofinalnosql.services.ImovelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/imovel")
public class ImovelController {

    @Autowired
    private ImovelService service;

    @GetMapping
    public ResponseEntity<List<ImovelDTO>> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ImovelDTO> findById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ImovelDTO> create(@RequestBody ImovelDTO employeeDTO) {
        return service.save(employeeDTO);
    }

    @PutMapping
    public ResponseEntity<ImovelDTO> update(@RequestBody ImovelDTO employeeDTO) {
        return service.update(employeeDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        return service.delete(id);
    }

}

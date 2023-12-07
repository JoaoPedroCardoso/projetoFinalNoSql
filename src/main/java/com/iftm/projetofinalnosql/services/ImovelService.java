package com.iftm.projetofinalnosql.services;

import com.iftm.projetofinalnosql.models.Imovel;
import com.iftm.projetofinalnosql.models.dtos.ImovelDTO;
import com.iftm.projetofinalnosql.repositories.ImovelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository repository;

    @Cacheable("all-imovel")
    public List<Imovel> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "imovel", key = "#id")
    public Optional<Imovel> findById(ObjectId id) {
        return repository.findById(id);
    }

    @CacheEvict(value = {"imovel", "all-imovel"}, allEntries = true)
    public Imovel save(Imovel imovel) {
        return repository.save(imovel);
    }

    @CacheEvict(value = {"imovel", "all-imovel"}, allEntries = true)
    public Imovel update(ImovelDTO imovelDTO, Imovel dbImovelObj) {
        dbImovelObj.setTipo_imovel(imovelDTO.getTipo_imovel());
        dbImovelObj.setArea(imovelDTO.getArea());
        dbImovelObj.setEndereco(imovelDTO.getEndereco());
        dbImovelObj.setPreco(imovelDTO.getPreco());
        return repository.save(dbImovelObj);
    }

    @CacheEvict(value = {"imovel", "all-imovel"}, allEntries = true)
    public void delete(ObjectId id) {
        repository.deleteById(id);
    }

}

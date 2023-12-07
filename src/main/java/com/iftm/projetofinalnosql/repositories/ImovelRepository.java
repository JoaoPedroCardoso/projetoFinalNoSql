package com.iftm.projetofinalnosql.repositories;

import com.iftm.projetofinalnosql.models.Imovel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends MongoRepository<Imovel, ObjectId> {

}

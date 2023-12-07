package com.iftm.projetofinalnosql.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imovel")
public class Imovel {

    @Id
    private ObjectId id;

    private String endereco;

    private String tipo_imovel;

    private String area;

    private String preco;

    public Imovel(ObjectId id, String endereco, String tipo_imovel, String area, String preco) {
        this.id = id;
        this.endereco = endereco;
        this.tipo_imovel = tipo_imovel;
        this.area = area;
        this.preco = preco;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo_imovel() {
        return tipo_imovel;
    }

    public void setTipo_imovel(String tipo_imovel) {
        this.tipo_imovel = tipo_imovel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}

package com.iftm.projetofinalnosql.models.dtos;

import com.iftm.projetofinalnosql.models.Imovel;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class ImovelDTO implements Serializable {

    private String id;

    private String endereco;

    private String tipo_imovel;

    private String area;

    private String preco;

    public ImovelDTO(String id, String endereco, String tipo_imovel, String area, String preco) {
        this.id = id;
        this.endereco = endereco;
        this.tipo_imovel = tipo_imovel;
        this.area = area;
        this.preco = preco;
    }

    public ImovelDTO(Imovel imovel) {
        if(imovel.getId() != null)
            this.id = imovel.getId().toString();
        this.endereco = imovel.getEndereco();
        this.tipo_imovel = imovel.getTipo_imovel();
        this.area = imovel.getArea();
        this.preco = imovel.getPreco();
    }

    public Imovel toImovel() {
        ObjectId id = null;
        if(this.id != null)
            id = new ObjectId(this.id);

        return new Imovel(id,
                this.endereco,
                this.tipo_imovel,
                this.area,
                this.preco);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

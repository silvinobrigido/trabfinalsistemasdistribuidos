package com.furb.mancala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MancalaBuraco implements Serializable {

    private Integer id;
    private Integer pedras;

    @JsonIgnore
    public Boolean estaVazio(){
        return this.pedras == 0;
    }

    public void limpa(){
        this.pedras = 0;
    }

    public void semea() {
        this.pedras++;
    }

    public void addPedras(Integer pedras){
        this.pedras+= pedras;
    }

    @Override
    public String toString() {
        return  id.toString() +
                ":" +
                pedras.toString() ;
    }
}

package com.furb.mancala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MancalaCova implements Serializable {

    private Integer id;
    private Integer sementes;

    @JsonIgnore
    public Boolean estaVazio(){
        return this.sementes == 0;
    }

    public void limpa(){
        this.sementes = 0;
    }

    public void semea() {
        this.sementes++;
    }

    public void addSementes(Integer sementes){
        this.sementes+= sementes;
    }

    @Override
    public String toString() {
        return  id.toString() +
                ":" +
                sementes.toString() ;
    }
}

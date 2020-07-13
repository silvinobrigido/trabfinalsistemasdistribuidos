package com.furb.mancalajogo.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furb.mancalajogo.excecoes.MancalaApiException;

import static com.furb.mancalajogo.constantes.MancalaConstantes.*;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "jogo")
@Setter
@Getter
public class MancalaJogo implements Serializable{
	@Id
    private String id;
    private List<MancalaCova> covas;
    private PartidaJogador partidaJogador;
    @JsonIgnore
    private int indiceAtual;
    public MancalaJogo() {
        this (sementesPadraoCova);
    }
    public MancalaJogo(int sementesDaCova) {
        this.covas = Arrays.asList(
                new MancalaCova(primeiraCovaJogadorA, sementesDaCova),
                new MancalaCova(segundaCovaJogadorA, sementesDaCova),
                new MancalaCova(terceiraCovaJogadorA, sementesDaCova),
                new MancalaCova(quartaCovaJogadorA, sementesDaCova),
                new MancalaCova(quintaCovaJogadorA, sementesDaCova),
                new MancalaCova(sextaCovaJogadorA, sementesDaCova),
                new MancalaCasa(covaDireitaId),
                new MancalaCova(primeiraCovaJogadorB, sementesDaCova),
                new MancalaCova(segundaCovaJogadorB, sementesDaCova),
                new MancalaCova(terceiraCovaJogadorB, sementesDaCova),
                new MancalaCova(quartaCovaJogadorB, sementesDaCova),
                new MancalaCova(quintaCovaJogadorB, sementesDaCova),
                new MancalaCova(sextaCovaJogadorB, sementesDaCova),
                new MancalaCasa(covaEsquerdaId));
    }
    public MancalaJogo(String id, Integer sementesDaCova) {
        this (sementesDaCova);
        this.id = id;
    }

    public MancalaCova getCova(Integer covaIdx) throws MancalaApiException {
        try {
            return this.covas.get(covaIdx-1);
        }catch (Exception e){
            throw  new MancalaApiException("Indice de cova :"+ covaIdx +" inválido!");
        }
    }
    
    @Override
    public String toString() {
        return "MancalaJogo{" +
                ", covas=" + covas +
                ", partidaJogador=" + partidaJogador +
                '}';
    }
}

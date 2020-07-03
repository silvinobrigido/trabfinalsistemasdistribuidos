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
    public MancalaJogo(int pitStones) {
        this.covas = Arrays.asList(
                new MancalaCova(primeiraCovaJogadorA, pitStones),
                new MancalaCova(segundaCovaJogadorA, pitStones),
                new MancalaCova(terceiraCovaJogadorA, pitStones),
                new MancalaCova(quartaCovaJogadorA, pitStones),
                new MancalaCova(quintaCovaJogadorA, pitStones),
                new MancalaCova(sextaCovaJogadorA, pitStones),
                new MancalaCasa(covaDireitaId),
                new MancalaCova(primeiraCovaJogadorB, pitStones),
                new MancalaCova(segundaCovaJogadorB, pitStones),
                new MancalaCova(terceiraCovaJogadorB, pitStones),
                new MancalaCova(quartaCovaJogadorB, pitStones),
                new MancalaCova(quintaCovaJogadorB, pitStones),
                new MancalaCova(sextaCovaJogadorB, pitStones),
                new MancalaCasa(covaEsquerdaId));
    }
    public MancalaJogo(String id, Integer pitStones) {
        this (pitStones);
        this.id = id;
    }
    // returns the corresponding pit of particular index
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

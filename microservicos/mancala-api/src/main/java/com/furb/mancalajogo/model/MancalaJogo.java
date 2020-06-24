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
    private List<MancalaBuraco> buracos;
    private PartidaJogador partidaJogador;
    @JsonIgnore
    private int indiceAtual;
    public MancalaJogo() {
        this (defaultPitStones);
    }
    public MancalaJogo(int pitStones) {
        this.buracos = Arrays.asList(
                new MancalaBuraco(firstPitPlayerA, pitStones),
                new MancalaBuraco(secondPitPlayerA, pitStones),
                new MancalaBuraco(thirdPitPlayerA, pitStones),
                new MancalaBuraco(forthPitPlayerA, pitStones),
                new MancalaBuraco(fifthPitPlayerA, pitStones),
                new MancalaBuraco(sixthPitPlayerA, pitStones),
                new MancalaCasa(rightPitHouseId),
                new MancalaBuraco(firstPitPlayerB, pitStones),
                new MancalaBuraco(secondPitPlayerB, pitStones),
                new MancalaBuraco(thirdPitPlayerB, pitStones),
                new MancalaBuraco(forthPitPlayerB, pitStones),
                new MancalaBuraco(fifthPitPlayerB, pitStones),
                new MancalaBuraco(sixthPitPlayerB, pitStones),
                new MancalaCasa(leftPitHouseId));
    }
    public MancalaJogo(String id, Integer pitStones) {
        this (pitStones);
        this.id = id;
    }
    // returns the corresponding pit of particular index
    public MancalaBuraco getBuraco(Integer pitIndex) throws MancalaApiException {
        try {
            return this.buracos.get(pitIndex-1);
        }catch (Exception e){
            throw  new MancalaApiException("Invalid pitIndex:"+ pitIndex +" has given!");
        }
    }
    @Override
    public String toString() {
        return "KalahaGame{" +
                ", pits=" + buracos +
                ", playerTurn=" + partidaJogador +
                '}';
    }
}

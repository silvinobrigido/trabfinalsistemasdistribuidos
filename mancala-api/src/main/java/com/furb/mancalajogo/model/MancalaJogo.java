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

@Document(collection = "games")
@Setter
@Getter
public class MancalaJogo implements Serializable{
	@Id
    private String id;
    private List<KalahaPit> pits;
    private PartidaJogador partidaJogador;
    @JsonIgnore
    private int indiceAtual;
    public MancalaJogo() {
        this (defaultPitStones);
    }
    public MancalaJogo(int pitStones) {
        this.pits = Arrays.asList(
                new KalahaPit(firstPitPlayerA, pitStones),
                new KalahaPit(secondPitPlayerA, pitStones),
                new KalahaPit(thirdPitPlayerA, pitStones),
                new KalahaPit(forthPitPlayerA, pitStones),
                new KalahaPit(fifthPitPlayerA, pitStones),
                new KalahaPit(sixthPitPlayerA, pitStones),
                new MancalaCasa(rightPitHouseId),
                new KalahaPit(firstPitPlayerB, pitStones),
                new KalahaPit(secondPitPlayerB, pitStones),
                new KalahaPit(thirdPitPlayerB, pitStones),
                new KalahaPit(forthPitPlayerB, pitStones),
                new KalahaPit(fifthPitPlayerB, pitStones),
                new KalahaPit(sixthPitPlayerB, pitStones),
                new MancalaCasa(leftPitHouseId));
    }
    public MancalaJogo(String id, Integer pitStones) {
        this (pitStones);
        this.id = id;
    }
    // returns the corresponding pit of particular index
    public KalahaPit getPit(Integer pitIndex) throws MancalaApiException {
        try {
            return this.pits.get(pitIndex-1);
        }catch (Exception e){
            throw  new MancalaApiException("Invalid pitIndex:"+ pitIndex +" has given!");
        }
    }
    @Override
    public String toString() {
        return "KalahaGame{" +
                ", pits=" + pits +
                ", playerTurn=" + partidaJogador +
                '}';
    }
}

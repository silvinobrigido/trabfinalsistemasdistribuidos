
package com.furb.mancala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

import static com.furb.mancala.web.constants.MancalaConstantes.*;

@Data
public class MancalaJogo {
    private String id;
    private String partidaJogador;
    private List <MancalaBuraco> buracos;

    @JsonIgnore
    public Integer getPedrasJogadorA(){
        return getBuraco(firstPitPlayerA).getPedras() +
                getBuraco(secondPitPlayerA).getPedras() +
                getBuraco(thirdPitPlayerA).getPedras()+
                getBuraco(forthPitPlayerA).getPedras()+
                getBuraco(fifthPitPlayerA).getPedras()+
                getBuraco(sixthPitPlayerA).getPedras();
    }

    @JsonIgnore
    public Integer getPedrasJogadorB() {
        return getBuraco(firstPitPlayerB).getPedras() +
                getBuraco(secondPitPlayerB).getPedras() +
                getBuraco(thirdPitPlayerB).getPedras()+
                getBuraco(forthPitPlayerB).getPedras()+
                getBuraco(fifthPitPlayerB).getPedras()+
                getBuraco(sixthPitPlayerB).getPedras();
    }

    public MancalaBuraco getBuraco (Integer pitIndex){
        return this.buracos.stream().filter(p -> p.getId() == pitIndex).findAny().get();
    }

    @JsonIgnore
    public Integer getPedrasLadoEsquerdo (){
        return getBuraco(leftPitHouseId).getPedras();
    }

    @JsonIgnore
    public Integer getPedrasLadoDireito (){
        return getBuraco(rightPitHouseId).getPedras();
    }

    @JsonIgnore
    public Integer getBuracoPedras(Integer indiceBuraco){
        return getBuraco(indiceBuraco).getPedras();
    }
}

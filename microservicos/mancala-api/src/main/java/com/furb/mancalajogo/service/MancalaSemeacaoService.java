package com.furb.mancalajogo.service;

import com.furb.mancalajogo.api.MancalaSemeacaoAPI;
import com.furb.mancalajogo.constantes.MancalaConstantes;
import com.furb.mancalajogo.model.MancalaBuraco;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.model.PartidaJogador;
import org.springframework.stereotype.Service;

@Service
public class MancalaSemeacaoService implements MancalaSemeacaoAPI {
	
    @Override
    public MancalaJogo semeia(MancalaJogo jogo, int indiceCasa) {
    
        if (indiceCasa == MancalaConstantes.rightPitHouseId || indiceCasa == MancalaConstantes.leftPitHouseId)
            return jogo;
        
        if (jogo.getPartidaJogador() == null) {
            if (indiceCasa < MancalaConstantes.rightPitHouseId)
            	jogo.setPartidaJogador(PartidaJogador.JogadorA);
            else
                jogo.setPartidaJogador(PartidaJogador.JogadorB);
        }
        // we need to check if request comes from the right player otherwise we do not sow the jogo. In other words,
        // we keep the turn for the correct player
        if (jogo.getPartidaJogador() == PartidaJogador.JogadorA && indiceCasa > MancalaConstantes.rightPitHouseId ||
        		jogo.getPartidaJogador() == PartidaJogador.JogadorB && indiceCasa < MancalaConstantes.rightPitHouseId)
            return jogo;
        MancalaBuraco selectedPit = jogo.getBuraco(indiceCasa);
        int stones = selectedPit.getPedras();
        // No movement for empty Pits
        if (stones == MancalaConstantes.emptyStone)
            return jogo;
        selectedPit.setPedras(MancalaConstantes.emptyStone);
        // keep the pit index, used for sowing the stones in right pits
        jogo.setIndiceAtual(indiceCasa);
        // simply sow all stones except the last one
        for (int i = 0; i < stones - 1; i++) {
        	semeaLadoDireito(jogo,false);
        }
        // simply the last stone
        semeaLadoDireito(jogo,true);
        int casaIndiceAtual = jogo.getIndiceAtual();
        // we switch the turn if the last sow was not on any of pit houses (left or right)
        if (casaIndiceAtual != MancalaConstantes.rightPitHouseId && casaIndiceAtual != MancalaConstantes.leftPitHouseId)
            jogo.setPartidaJogador(nextTurn(jogo.getPartidaJogador()));
        return jogo;
    }
    // sow the jogo one pit to the right
    private void semeaLadoDireito(MancalaJogo jogo, Boolean ultimaPedra) {
        int casaIndiceAtual = jogo.getIndiceAtual() % MancalaConstantes.totalPits + 1;
        PartidaJogador PartidaJogador = jogo.getPartidaJogador();
        if ((casaIndiceAtual == MancalaConstantes.rightPitHouseId && PartidaJogador == PartidaJogador.JogadorB) ||
                (casaIndiceAtual == MancalaConstantes.leftPitHouseId && PartidaJogador == PartidaJogador.JogadorA))
            casaIndiceAtual = casaIndiceAtual % MancalaConstantes.totalPits + 1;
        jogo.setIndiceAtual(casaIndiceAtual);
        MancalaBuraco buracoSelecionado = jogo.getBuraco(casaIndiceAtual);
        if (!ultimaPedra || casaIndiceAtual == MancalaConstantes.rightPitHouseId || casaIndiceAtual == MancalaConstantes.leftPitHouseId) {
        	buracoSelecionado.semea();
            return;
        }
        // It's the last stone and we need to check the opposite player's pit status
        MancalaBuraco  buracoOposto = jogo.getBuraco(MancalaConstantes.totalPits - casaIndiceAtual);
        
        if (buracoSelecionado.isEmpty() && !buracoOposto.isEmpty()) {
            Integer pedrasOpostas = buracoOposto.getPedras();
            buracoOposto.clear();
            Integer pitHouseIndex = (casaIndiceAtual < MancalaConstantes.rightPitHouseId 
            		              ? MancalaConstantes.rightPitHouseId : MancalaConstantes.leftPitHouseId);
            MancalaBuraco pitHouse = jogo.getBuraco(pitHouseIndex);
            pitHouse.addPedras(pedrasOpostas + 1);
            return;
        }
        buracoSelecionado.semea();
    }
    public PartidaJogador nextTurn(PartidaJogador partidaAtual) {
        if (partidaAtual == PartidaJogador.JogadorA)
            return PartidaJogador.JogadorB;
        return PartidaJogador.JogadorA;
    }
}

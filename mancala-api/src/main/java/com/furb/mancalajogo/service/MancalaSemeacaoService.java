package com.furb.mancalajogo.service;

import com.furb.mancalajogo.api.MancalaSemeacaoAPI;
import com.furb.mancalajogo.constantes.MancalaConstantes;
import com.furb.mancalajogo.model.KalahaPit;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.model.PartidaJogador;

public class MancalaSemeacaoService implements MancalaSemeacaoAPI {
	// This method perform sowing the jogo on specific pit index
    @Override
    public MancalaJogo semeia(MancalaJogo jogo, int indiceCasa) {
        // No movement on House pits
        if (indiceCasa == MancalaConstantes.rightPitHouseId || indiceCasa == MancalaConstantes.leftPitHouseId)
            return jogo;
        // we set the player turn for the first move of the jogo based on the pit id
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
        KalahaPit selectedPit = jogo.getPit(indiceCasa);
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
        KalahaPit targetPit = jogo.getPit(casaIndiceAtual);
        if (!ultimaPedra || casaIndiceAtual == MancalaConstantes.rightPitHouseId || casaIndiceAtual == MancalaConstantes.leftPitHouseId) {
            targetPit.semea();
            return;
        }
        // It's the last stone and we need to check the opposite player's pit status
        KalahaPit  buracoOposto = jogo.getPit(MancalaConstantes.totalPits - casaIndiceAtual);
        // we are sowing the last stone and the current player's pit is empty but the opposite pit is not empty, therefore,
        // we collect the opposite's Pit stones plus the last stone and add them to the House Pit of current player and
        // make the opposite Pit empty
        if (targetPit.isEmpty() && !buracoOposto.isEmpty()) {
            Integer pedrasOpostas = buracoOposto.getPedras();
            buracoOposto.clear();
            Integer pitHouseIndex = (casaIndiceAtual < MancalaConstantes.rightPitHouseId 
            		              ? MancalaConstantes.rightPitHouseId : MancalaConstantes.leftPitHouseId);
            KalahaPit pitHouse = jogo.getPit(pitHouseIndex);
            pitHouse.addPedras(pedrasOpostas + 1);
            return;
        }
        targetPit.semea();
    }
    public PartidaJogador nextTurn(PartidaJogador partidaAtual) {
        if (partidaAtual == PartidaJogador.JogadorA)
            return PartidaJogador.JogadorB;
        return PartidaJogador.JogadorA;
    }
}

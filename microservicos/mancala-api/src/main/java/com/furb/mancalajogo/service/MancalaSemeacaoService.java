package com.furb.mancalajogo.service;

import com.furb.mancalajogo.api.MancalaSemeacaoAPI;
import com.furb.mancalajogo.constantes.MancalaConstantes;
import com.furb.mancalajogo.model.MancalaCova;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.model.PartidaJogador;
import org.springframework.stereotype.Service;

@Service
public class MancalaSemeacaoService implements MancalaSemeacaoAPI {
	
    @Override
    public MancalaJogo semeia(MancalaJogo jogo, int indiceCova) {
    
        if (indiceCova == MancalaConstantes.covaDireitaId || indiceCova == MancalaConstantes.covaEsquerdaId)
            return jogo;
        
        if (jogo.getPartidaJogador() == null) {
            if (indiceCova < MancalaConstantes.covaDireitaId)
            	jogo.setPartidaJogador(PartidaJogador.JogadorA);
            else
                jogo.setPartidaJogador(PartidaJogador.JogadorB);
        }
        
        if (jogo.getPartidaJogador() == PartidaJogador.JogadorA && indiceCova > MancalaConstantes.covaDireitaId ||
        		jogo.getPartidaJogador() == PartidaJogador.JogadorB && indiceCova < MancalaConstantes.covaDireitaId)
            return jogo;
        MancalaCova covaSelecionada = jogo.getCova(indiceCova);
        int semente = covaSelecionada.getSementes();
        
        if (semente == MancalaConstantes.sementeZero)
            return jogo;
        covaSelecionada.setSementes(MancalaConstantes.sementeZero);
        
        jogo.setIndiceAtual(indiceCova);
        
        for (int i = 0; i < semente - 1; i++) {
        	semeaLadoDireito(jogo,false);
        }
        
        semeaLadoDireito(jogo,true);
        int casaIndiceAtual = jogo.getIndiceAtual();
        
        if (casaIndiceAtual != MancalaConstantes.covaDireitaId && casaIndiceAtual != MancalaConstantes.covaEsquerdaId)
            jogo.setPartidaJogador(nextTurn(jogo.getPartidaJogador()));
        return jogo;
    }
   
    private void semeaLadoDireito(MancalaJogo jogo, Boolean ultimaSemente) {
        int casaIndiceAtual = jogo.getIndiceAtual() % MancalaConstantes.totalBuracos + 1;
        PartidaJogador PartidaJogador = jogo.getPartidaJogador();
        if ((casaIndiceAtual == MancalaConstantes.covaDireitaId && PartidaJogador == PartidaJogador.JogadorB) ||
                (casaIndiceAtual == MancalaConstantes.covaEsquerdaId && PartidaJogador == PartidaJogador.JogadorA))
            casaIndiceAtual = casaIndiceAtual % MancalaConstantes.totalBuracos + 1;
        jogo.setIndiceAtual(casaIndiceAtual);
        MancalaCova covaSelecionada = jogo.getCova(casaIndiceAtual);
        if (!ultimaSemente || casaIndiceAtual == MancalaConstantes.covaDireitaId || casaIndiceAtual == MancalaConstantes.covaEsquerdaId) {
        	covaSelecionada.semea();
            return;
        }

        MancalaCova covaOposta = jogo.getCova(MancalaConstantes.totalBuracos - casaIndiceAtual);
        
        if (covaSelecionada.isEmpty() && !covaOposta.isEmpty()) {
            Integer sementesOpostas = covaOposta.getSementes();
            covaOposta.limpa();
            Integer idxcasaCova = (casaIndiceAtual < MancalaConstantes.covaDireitaId 
            		              ? MancalaConstantes.covaDireitaId : MancalaConstantes.covaEsquerdaId);
            MancalaCova casaCova = jogo.getCova(idxcasaCova);
            casaCova.addSementes(sementesOpostas + 1);
            return;
        }
        covaSelecionada.semea();
    }
    public PartidaJogador nextTurn(PartidaJogador partidaAtual) {
        if (partidaAtual == PartidaJogador.JogadorA)
            return PartidaJogador.JogadorB;
        return PartidaJogador.JogadorA;
    }
}

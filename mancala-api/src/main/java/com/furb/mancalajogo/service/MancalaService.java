package com.furb.mancalajogo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.furb.mancalajogo.api.MancalaApi;
import com.furb.mancalajogo.excecoes.RecursoNaoEncontradoException;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.repository.MancalaRepositorioJogo;
import java.util.Optional;

public class MancalaService implements MancalaApi{
    @Autowired
	private MancalaRepositorioJogo repositorio;
	
	@Override
	public MancalaJogo criarJogo(int pedras) {
		MancalaJogo jogo = new MancalaJogo(pedras);
		repositorio.save(jogo);
		
		return jogo;
	}
	
	@Cacheable (value = "kalahGames", key = "#id" , unless = "#result  == null")
    public MancalaJogo loadGame(String id) throws RecursoNaoEncontradoException {
        Optional<MancalaJogo> gameOptional = repositorio.findById(id);
        if (!gameOptional.isPresent())
            throw new RecursoNaoEncontradoException("Game id " + id + " not found!");
        return gameOptional.get();
    }
    
    @CachePut(value = "kalahGames", key = "#kalahaGame.id")
    public MancalaJogo updateGame (MancalaJogo jogo){
    	jogo = repositorio.save(jogo);
        return jogo;
    }

}
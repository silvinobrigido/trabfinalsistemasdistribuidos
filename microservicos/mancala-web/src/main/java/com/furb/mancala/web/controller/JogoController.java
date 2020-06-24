package com.furb.mancala.web.controller;

import com.furb.mancala.web.client.*;
import com.furb.mancala.web.events.SemeaEvento;
import com.furb.mancala.web.exceptions.ApiConnectionException;

import com.furb.mancala.web.model.MancalaJogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class JogoController {

    private MancalaJogo jogo;

    @Autowired
    private MancalaClient cliente;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public MancalaJogo iniciaNovoJogo() throws ApiConnectionException {
        try {
            this.jogo = cliente.iniciaNovoJogoMancala();

            return this.jogo;

        }catch (Exception e){
            throw new ApiConnectionException("Não foi possível se conectar ao serviço! \n" + e.getMessage() );
        }
    }

    public void semeia(Integer indiceBuraco) throws ApiConnectionException {
        try {
            this.jogo = cliente.semeaJogoMancala(this.jogo.getId(), indiceBuraco);

            this.eventPublisher.publishEvent(new SemeaEvento(this, this.jogo, indiceBuraco));

        }catch (Exception ex){
            throw new ApiConnectionException("Não foi possível se conectar ao serviço! \n" + ex.getMessage() );
        }
    }

    public boolean jogoIniciado() {
        return this.jogo != null;
    }
}


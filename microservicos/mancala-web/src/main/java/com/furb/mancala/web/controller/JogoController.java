package com.furb.mancala.web.controller;

import com.furb.mancala.web.client.*;
import com.furb.mancala.web.events.SemeaEvento;
import com.furb.mancala.web.exceptions.ApiConnectionException;

import com.furb.mancala.web.model.MancalaJogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
/*
    This class acts as Controller for the web application
 */
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
            throw new ApiConnectionException("Error connecting to Mancala service!");
        }
    }

    public void sow(Integer pitIndex) throws ApiConnectionException {
        try {
            this.jogo = cliente.semeaJogoMancala(this.jogo.getId(), pitIndex);

            this.eventPublisher.publishEvent(new SemeaEvento(this, this.jogo, pitIndex));

        }catch (Exception ex){
            throw new ApiConnectionException("Error connecting to Mancala service!");
        }
    }

    public boolean jogoIniciado() {
        return this.jogo != null;
    }
}


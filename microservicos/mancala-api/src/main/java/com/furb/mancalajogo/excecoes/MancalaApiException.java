package com.furb.mancalajogo.excecoes;

public class MancalaApiException extends RuntimeException {
    public MancalaApiException(String mensagem) {
        super(mensagem);
    }
}

package com.furb.mancalajogo.model;
import lombok.Getter;

@Getter
public enum PartidaJogador {
	
	JogadorA ("A"), JogadorB ("B");

    private String partida;

	private PartidaJogador(String partida) {
		this.partida = partida;
	}
	
	@Override
    public String toString() {
        return partida;
    }
}

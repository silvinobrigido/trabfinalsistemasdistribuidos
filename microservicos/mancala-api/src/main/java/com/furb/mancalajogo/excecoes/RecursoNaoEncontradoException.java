package com.furb.mancalajogo.excecoes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends Exception {
	
    private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String mensagem){
	        super(mensagem);
	}
}

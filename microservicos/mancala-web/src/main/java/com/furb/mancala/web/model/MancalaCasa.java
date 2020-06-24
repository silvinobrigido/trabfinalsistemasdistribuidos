package com.furb.mancala.web.model;

import static com.furb.mancala.web.constants.MancalaConstantes.pedraZero;

public class MancalaCasa extends MancalaBuraco {
	
    public MancalaCasa(Integer id) {
        super(id , pedraZero);
    }
}


package com.furb.mancala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

import static com.furb.mancala.web.constants.MancalaConstantes.*;

@Data
public class MancalaJogo {
    private String id;
    private String partidaJogador;
    private List <MancalaCova> covas;

    @JsonIgnore
    public Integer getSementesJogadorA(){
        return getCova(primeiraCovaJogadorA).getSementes() +
               getCova(segundaCovaJogadorA).getSementes()  +
               getCova(terceiraCovaJogadorA).getSementes() +
               getCova(quartaCovaJogadorA).getSementes()   +
               getCova(quintaCovaJogadorA).getSementes()   +
               getCova(sextaCovaJogadorA).getSementes();
    }

    @JsonIgnore
    public Integer getSementesJogadorB() {
        return getCova(primeiraCovaJogadorB).getSementes() +
               getCova(segundaCovaJogadorB).getSementes()  +
               getCova(terceiraCovaJogadorB).getSementes() +
               getCova(quartaCovaJogadorB).getSementes()   +
               getCova(quintaCovaJogadorB).getSementes()   +
               getCova(sextaCovaJogadorB).getSementes();
    }

    public MancalaCova getCova(Integer indiceCova) {
        return this.covas.stream().filter(p -> p.getId() == indiceCova).findAny().get();
    }

    @JsonIgnore
    public Integer getSementesLadoEsquerdo() {
        return getCova(covaEsquerdaId).getSementes();
    }

    @JsonIgnore
    public Integer getSementesLadoDireito() {
        return getCova(covaDireitaId).getSementes();
    }

    @JsonIgnore
    public Integer getSementesCova(Integer indiceCova){
        return getCova(indiceCova).getSementes();
    }
}

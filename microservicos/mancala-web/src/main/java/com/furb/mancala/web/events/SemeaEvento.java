
package com.furb.mancala.web.events;

import com.furb.mancala.web.model.MancalaJogo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SemeaEvento extends ApplicationEvent {

    private MancalaJogo jogo;
    private Integer pitIndex;
    public SemeaEvento(Object source, MancalaJogo jogo, Integer pitIndex) {
        super(source);
        this.jogo = jogo;
        this.pitIndex = pitIndex;
    }
}

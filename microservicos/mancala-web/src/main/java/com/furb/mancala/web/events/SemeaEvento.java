
package com.furb.mancala.web.events;

import com.furb.mancala.web.model.MancalaJogo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/*
    This event is fired when user clicks on any pit to sow the jogo. As a result of this event, a call is made to

    Mancala Api and the application is filled with the results of sowing the pit for selected index
 */

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

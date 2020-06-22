
package com.furb.mancala.web.view;

import com.furb.mancala.web.controller.JogoController;
import com.furb.mancala.web.model.MancalaJogo;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import static com.furb.mancala.web.constants.MancalaConstantes.*;

@SpringComponent
@UIScope
public class BuracoLayoutComponent extends VerticalLayout implements KeyNotifier {

    private BuracoComponent pit1 ;
    private BuracoComponent pit2 ;
    private BuracoComponent pit3 ;
    private BuracoComponent pit4 ;
    private BuracoComponent pit5 ;
    private BuracoComponent pit6 ;

    private BuracoComponent pit8 ;
    private BuracoComponent pit9 ;
    private BuracoComponent pit10;
    private BuracoComponent pit11 ;
    private BuracoComponent pit12 ;
    private BuracoComponent pit13 ;

    public BuracoLayoutComponent(JogoController jogoController) {
        this.pit1 = new BuracoComponent(firstPitPlayerA, jogoController);
        this.pit2 = new BuracoComponent(secondPitPlayerA, jogoController);
        this.pit3 = new BuracoComponent(thirdPitPlayerA, jogoController);
        this.pit4 = new BuracoComponent(forthPitPlayerA, jogoController);
        this.pit5 = new BuracoComponent(fifthPitPlayerA, jogoController);
        this.pit6 = new BuracoComponent(sixthPitPlayerA, jogoController);

        this.pit8 = new BuracoComponent(firstPitPlayerB, jogoController);
        this.pit9 = new BuracoComponent(secondPitPlayerB, jogoController);
        this.pit10 = new BuracoComponent(thirdPitPlayerB, jogoController);
        this.pit11 = new BuracoComponent(forthPitPlayerB, jogoController);
        this.pit12 = new BuracoComponent(fifthPitPlayerB, jogoController);
        this.pit13 = new BuracoComponent(sixthPitPlayerB, jogoController);
        HorizontalLayout playerAPits = new HorizontalLayout(pit1, pit2, pit3, pit4, pit5, pit6);
        HorizontalLayout playerBPits = new HorizontalLayout(pit13, pit12, pit11, pit10, pit9, pit8);

        add(playerBPits, playerAPits);
    }

    public void fillPitStones (MancalaJogo jogo){
        this.pit1.setPedras(jogo.getBuracoPedras(firstPitPlayerA).toString());
        this.pit2.setPedras(jogo.getBuracoPedras(secondPitPlayerA).toString());
        this.pit3.setPedras(jogo.getBuracoPedras(thirdPitPlayerA).toString());
        this.pit4.setPedras(jogo.getBuracoPedras(forthPitPlayerA).toString());
        this.pit5.setPedras(jogo.getBuracoPedras(fifthPitPlayerA).toString());
        this.pit6.setPedras(jogo.getBuracoPedras(sixthPitPlayerA).toString());
        this.pit8.setPedras(jogo.getBuracoPedras(firstPitPlayerB).toString());
        this.pit9.setPedras(jogo.getBuracoPedras(secondPitPlayerB).toString());
        this.pit10.setPedras(jogo.getBuracoPedras(thirdPitPlayerB).toString());
        this.pit11.setPedras(jogo.getBuracoPedras(forthPitPlayerB).toString());
        this.pit12.setPedras(jogo.getBuracoPedras(fifthPitPlayerB).toString());
        this.pit13.setPedras(jogo.getBuracoPedras(sixthPitPlayerB).toString());
    }
}

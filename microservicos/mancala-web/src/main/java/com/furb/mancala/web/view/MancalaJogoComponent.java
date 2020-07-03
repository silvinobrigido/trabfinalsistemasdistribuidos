
package com.furb.mancala.web.view;

import com.furb.mancala.web.controller.JogoController;
import com.furb.mancala.web.events.SemeaEvento;
import com.furb.mancala.web.model.MancalaJogo;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@UIScope
@SpringComponent
@Slf4j
@Component
public class MancalaJogoComponent extends VerticalLayout implements KeyNotifier {

    private final CovaLayoutComponent CovaLayoutComponent;

    private final CovaComponent rightHouse;
    private final CovaComponent leftHouse;

    final Label playerTurnLabel;
    final TextField playerTurnTextField;

    final Label winLabel;


    public MancalaJogoComponent(CovaLayoutComponent CovaLayoutComponent, @Autowired JogoController gameController) {
        this.CovaLayoutComponent = CovaLayoutComponent;

        this.playerTurnLabel = new Label("Jogador Partida:");
        this.playerTurnTextField = new TextField("");
        this.playerTurnTextField.setReadOnly(true);

        // build layout for game information
        HorizontalLayout turnLayout = new HorizontalLayout(playerTurnLabel, playerTurnTextField);
        turnLayout.setAlignItems(Alignment.CENTER);
        add(turnLayout);

        rightHouse = new CovaComponent(7 , gameController);
        rightHouse.setAlignItems(Alignment.CENTER);
        rightHouse.add(new Label("Jogador A"));

        leftHouse = new CovaComponent(14, gameController);
        leftHouse.setAlignItems(Alignment.CENTER);
        leftHouse.add(new Label("Jogador B"));

        HorizontalLayout gameLayout = new HorizontalLayout(leftHouse, CovaLayoutComponent, rightHouse);
        gameLayout.setAlignItems(Alignment.CENTER);

        add(gameLayout);

        // Adding the win layout
        winLabel = new Label("");
        winLabel.setVisible(false);
        winLabel.getStyle().set("font-size", "50px");
        winLabel.getStyle().set("color", "#ff0000");

        HorizontalLayout winLayout = new HorizontalLayout(winLabel);
        winLayout.setAlignItems(Alignment.CENTER);

        add(winLayout);

        setAlignItems(Alignment.CENTER);
    }

    public TextField getPlayerTurnTextField() {
        return playerTurnTextField;
    }

    public void preencheMancala(MancalaJogo jogo) {
        this.leftHouse.setSementes(jogo.getSementesLadoEsquerdo().toString());
        this.rightHouse.setSementes(jogo.getSementesLadoDireito().toString());
        this.CovaLayoutComponent.preencheCovas(jogo);
    }

    public void novoJogo(MancalaJogo game) {
        this.preencheMancala(game);
        this.winLabel.setVisible(false);
    }

    @EventListener
    public void handleFlushEvent (SemeaEvento evento) {
        MancalaJogo jogo = evento.getJogo();
        this.preencheMancala(jogo);
        this.playerTurnTextField.setValue(evento.getJogo().getPartidaJogador());

        Integer playerARemainingStones = jogo.getSementesJogadorA();
        Integer playerBRemainingStones = jogo.getSementesJogadorB();

        if (playerARemainingStones == 0 || playerBRemainingStones ==0){
            Integer totalA = playerARemainingStones + jogo.getSementesLadoDireito();
            Integer totalB = playerBRemainingStones + jogo.getSementesLadoEsquerdo();

            this.leftHouse.setSementes(totalB.toString());
            this.rightHouse.setSementes(totalA.toString());

            if (totalA > totalB)
                this.winLabel.setText("Fim de Jogo! Jogador A Venceu!!!");
            else
                this.winLabel.setText("Fim de Jogo! Jogador B Venceu!!!");

            this.winLabel.setVisible(true);
        }
    }
}

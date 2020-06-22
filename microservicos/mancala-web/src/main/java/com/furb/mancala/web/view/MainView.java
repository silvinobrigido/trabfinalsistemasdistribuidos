
package com.furb.mancala.web.view;

import com.furb.mancala.web.controller.JogoController;
import com.furb.mancala.web.exceptions.ApiConnectionException;
import com.furb.mancala.web.model.MancalaJogo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route ("mancala")
@Slf4j
public class MainView extends VerticalLayout {

	private final Button startGameBtn;

	final Label gameIdLabel;
	final TextField gameIdTextField;

	@Autowired
	private JogoController jogoController;

	final MancalaJogoComponent mancalaJogoComponent;

	public MainView(MancalaJogoComponent MancalaJogoComponent, JogoController jogoController) {
		this.mancalaJogoComponent = MancalaJogoComponent;
		this.jogoController = jogoController;

		// build the game information layout
		this.startGameBtn = new Button("Inicar Jogo");
		this.gameIdLabel = new Label("Código de Jogo:");
		this.gameIdTextField = new TextField("", "", "");
		this.gameIdTextField.setReadOnly(true);
		this.gameIdTextField.setMinLength(50);

		// build layout for game id
		HorizontalLayout gameIdLayout = new HorizontalLayout(gameIdLabel, gameIdTextField);
		gameIdLayout.setAlignItems(Alignment.CENTER);
		add(gameIdLayout);

		// adding the game itself
		add(MancalaJogoComponent);

		// build layout for actions
		HorizontalLayout actions = new HorizontalLayout(startGameBtn);
		add(actions);
        
        startGameBtn.addClickListener(e -> {
            try {
                MancalaJogo jogo = this.jogoController.iniciaNovoJogo();
                mancalaJogoComponent.novoJogo(jogo);
                this.gameIdTextField.setValue(jogo.getId());
                this.mancalaJogoComponent.getPlayerTurnTextField().setValue("");

                Notification.show("Novo Jogo Iniciado. id:" + jogo.getId(), 3000, Notification.Position.MIDDLE);

            } catch (ApiConnectionException ex) {
                Notification.show("Erro!" + ex.getMessage());
                log.error(ex.getMessage(), ex);
            }
        });
	}
}

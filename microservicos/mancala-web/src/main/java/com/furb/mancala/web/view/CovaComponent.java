package com.furb.mancala.web.view;

import com.furb.mancala.web.controller.JogoController;
import com.furb.mancala.web.exceptions.ApiConnectionException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@UIScope
@SpringComponent
@Getter
@Slf4j
public class CovaComponent extends VerticalLayout {

    private static final Integer defaultburacoStones = 0;

    private final TextField buraco = new TextField();
    private final Button btn = new Button();

    private JogoController jogoController;

    public CovaComponent() {
        buraco.getElement().setAttribute("theme", "align-center");
        buraco.setReadOnly(true);
        buraco.setValue(defaultburacoStones.toString());
        buraco.getStyle().set("font-size", "15px");
        buraco.getStyle().set("font-weight", "bold");
        buraco.setMaxLength(30);
        buraco.setMinLength(30);
        btn.getElement().setAttribute("theme", "align-center");
        add(btn, buraco);
        setAlignItems(Alignment.CENTER);

        buraco.addValueChangeListener(e -> {
            buraco.getStyle().set("background-color", "#ff9933");
            new ChangeColorThread(UI.getCurrent(), buraco).start();
        });
    }

    private static class ChangeColorThread extends Thread{

        private UI ui;
        private TextField textField;
        public ChangeColorThread(UI ui, TextField textField) {
            this.ui = ui;
            this.textField = textField;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            ui.access(() -> {
                textField.getStyle().set("background-color", "#ffffff");
            });
        }
    }

    public CovaComponent(Integer indiceCova, JogoController jogoController) {
        this();
        this.jogoController = jogoController;
        buraco.setId(indiceCova.toString());

        btn.setText(indiceCova.toString());
        btn.setTabIndex(indiceCova);
        btn.addClickListener(e -> {
            if (!this.jogoController.jogoIniciado()){
                Notification.show("Inicie o jogo!");
                return;
            }

            Notification.show(e.getSource().getTabIndex() + " ");
            try {
                this.jogoController.semeia(e.getSource().getTabIndex());
            } catch (ApiConnectionException ex) {
                log.error(ex.getMessage(), ex);
                Notification.show("Erro ao conectar ao servidor");
            }
        });
    }

    public void setSementes(String sementes) {
        this.buraco.setValue(sementes);
    }
}

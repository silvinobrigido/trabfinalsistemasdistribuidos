/**
 * Copyright 2019 Esfandiyar Talebi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class BuracoComponent extends VerticalLayout {

    private static final Integer defaultburacoStones = 0;

    private final TextField buraco = new TextField();
    private final Button btn = new Button();

    private JogoController jogoController;

    public BuracoComponent() {
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

    public BuracoComponent(Integer indiceBuraco, JogoController jogoController) {
        this();
        this.jogoController = jogoController;
        buraco.setId(indiceBuraco.toString());

        btn.setText(indiceBuraco.toString());
        btn.setTabIndex(indiceBuraco);
        btn.addClickListener(e -> {
            if (!this.jogoController.jogoIniciado()){
                Notification.show("Please click on 'Start Game' button to start the game first!");
                return;
            }

            Notification.show(e.getSource().getTabIndex() + " Clicked");
            try {
                this.jogoController.sow(e.getSource().getTabIndex());
            } catch (ApiConnectionException ex) {
                log.error(ex.getMessage(), ex);
                Notification.show("Error connecting to the server!. Try later");
            }
        });
    }

    public void setPedras(String pedras) {
        this.buraco.setValue(pedras);
    }
}

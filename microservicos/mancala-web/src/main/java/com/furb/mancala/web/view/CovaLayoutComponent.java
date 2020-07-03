
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
public class CovaLayoutComponent extends VerticalLayout implements KeyNotifier {

    private CovaComponent cova1 ;
    private CovaComponent cova2 ;
    private CovaComponent cova3 ;
    private CovaComponent cova4 ;
    private CovaComponent cova5 ;
    private CovaComponent cova6 ;

    private CovaComponent cova8 ;
    private CovaComponent cova9 ;
    private CovaComponent cova10;
    private CovaComponent cova11 ;
    private CovaComponent cova12 ;
    private CovaComponent cova13 ;

    public CovaLayoutComponent(JogoController jogoController) {
        this.cova1 = new CovaComponent(primeiraCovaJogadorA, jogoController);
        this.cova2 = new CovaComponent(segundaCovaJogadorA, jogoController);
        this.cova3 = new CovaComponent(terceiraCovaJogadorA, jogoController);
        this.cova4 = new CovaComponent(quartaCovaJogadorA, jogoController);
        this.cova5 = new CovaComponent(quintaCovaJogadorA, jogoController);
        this.cova6 = new CovaComponent(sextaCovaJogadorA, jogoController);

        this.cova8 = new CovaComponent(primeiraCovaJogadorB, jogoController);
        this.cova9 = new CovaComponent(segundaCovaJogadorB, jogoController);
        this.cova10 = new CovaComponent(terceiraCovaJogadorB, jogoController);
        this.cova11 = new CovaComponent(quartaCovaJogadorB, jogoController);
        this.cova12 = new CovaComponent(quintaCovaJogadorB, jogoController);
        this.cova13 = new CovaComponent(sextaCovaJogadorB, jogoController);
        HorizontalLayout jogadorACova = new HorizontalLayout(cova1, cova2, cova3, cova4, cova5, cova6);
        HorizontalLayout jogadorBCova = new HorizontalLayout(cova13, cova12, cova11, cova10, cova9, cova8);

        add(jogadorBCova, jogadorACova);
    }

    public void preencheCovas(MancalaJogo jogo){
        this.cova1.setSementes(jogo.getSementesCova(primeiraCovaJogadorA).toString());
        this.cova2.setSementes(jogo.getSementesCova(segundaCovaJogadorA).toString());
        this.cova3.setSementes(jogo.getSementesCova(terceiraCovaJogadorA).toString());
        this.cova4.setSementes(jogo.getSementesCova(quartaCovaJogadorA).toString());
        this.cova5.setSementes(jogo.getSementesCova(quintaCovaJogadorA).toString());
        this.cova6.setSementes(jogo.getSementesCova(sextaCovaJogadorA).toString());
        this.cova8.setSementes(jogo.getSementesCova(primeiraCovaJogadorB).toString());
        this.cova9.setSementes(jogo.getSementesCova(segundaCovaJogadorB).toString());
        this.cova10.setSementes(jogo.getSementesCova(terceiraCovaJogadorB).toString());
        this.cova11.setSementes(jogo.getSementesCova(quartaCovaJogadorB).toString());
        this.cova12.setSementes(jogo.getSementesCova(quintaCovaJogadorB).toString());
        this.cova13.setSementes(jogo.getSementesCova(sextaCovaJogadorB).toString());
    }
}

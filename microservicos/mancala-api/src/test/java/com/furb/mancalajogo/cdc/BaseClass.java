package com.furb.mancalajogo.cdc;


import com.furb.mancalajogo.controller.MancalaController;
import com.furb.mancalajogo.model.MancalaBuraco;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.model.PartidaJogador;
import com.furb.mancalajogo.service.MancalaSemeacaoService;
import com.furb.mancalajogo.service.MancalaService;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@Import({MancalaController.class, MancalaService.class})
@DirtiesContext
public class BaseClass {

    @LocalServerPort
    private String port;

    @MockBean
    private MancalaService mancalaGameService;

    @MockBean
    private MancalaSemeacaoService mancalaSowingService;

    @Autowired
    private MancalaController mancalaController;

    @Before
    public void setupGame() throws Exception {
        RestAssured.baseURI = "http://localhost:" + this.port;

        MancalaJogo expectedGame = new MancalaJogo("5d414dcd24e4990006e7c900", 6);

        Mockito.when(this.mancalaGameService.criarJogo(6))
                .thenReturn(expectedGame);

        Mockito.when(mancalaGameService.loadGame("5d414dcd24e4990006e7c900"))
                .thenReturn(expectedGame);

        List<MancalaBuraco> pitsAfterSowingIndex2 = Arrays.asList(
                new MancalaBuraco(1, 6),
                new MancalaBuraco(2, 0),
                new MancalaBuraco(3, 7),
                new MancalaBuraco(4, 7),
                new MancalaBuraco(5, 7),
                new MancalaBuraco(6, 7),
                new MancalaBuraco(7, 1),
                new MancalaBuraco(8, 7),
                new MancalaBuraco(9, 6),
                new MancalaBuraco(10, 6),
                new MancalaBuraco(11, 6),
                new MancalaBuraco(12, 6),
                new MancalaBuraco(13, 6),
                new MancalaBuraco(14, 0));

        MancalaJogo gameAfterSowingIndex2 = new MancalaJogo("5d414dcd24e4990006e7c900", 6);
        gameAfterSowingIndex2.setBuracos(pitsAfterSowingIndex2);
        gameAfterSowingIndex2.setPartidaJogador(PartidaJogador.JogadorB);

        Mockito.when(this.mancalaSowingService.semeia(expectedGame, 2))
                .thenReturn(gameAfterSowingIndex2);

        RestAssuredMockMvc.standaloneSetup(mancalaController);
    }
}

package com.furb.mancala.web.client;

import com.furb.mancala.web.MancalaWebApplication;
import com.furb.mancala.web.model.MancalaJogo;
import com.furb.mancala.web.model.MancalaBuraco;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/*
    Using Spring Cloud Contract Wiremock for inter-service communication testing between Mancala microservices
 */

@SpringBootTest (classes = MancalaWebApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureWireMock (port = 8080)
@DirtiesContext
public class MancalaIntegrationTests {

    private final Resource mancalaCreate = new ClassPathResource("mancala-creation.json");

    private final Resource mancalaSow2 = new ClassPathResource("mancala-sow-2.json");

    @MockBean
    MancalaClientConfig mancalaClientConfig;

    @Autowired
    private MancalaClient mancalaClient;

    @SneakyThrows
    private String asJson(Resource resource) {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    @Test
    public void testManacalaCreation () throws Exception{

        Mockito.when(mancalaClientConfig.getNewMancalaGameUrl()).thenReturn("http://localhost:8080/games");

        WireMock.stubFor(WireMock.post("/games")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson(mancalaCreate))
                ));

        MancalaJogo mancalaJogo = mancalaClient.iniciaNovoJogoMancala();

        List<MancalaBuraco> MancalaBuracos = Arrays.asList(
                new MancalaBuraco(1 , 6),
                new MancalaBuraco(2 , 6),
                new MancalaBuraco(3 , 6),
                new MancalaBuraco(4 , 6),
                new MancalaBuraco(5 , 6),
                new MancalaBuraco(6 , 6),
                new MancalaBuraco(7 , 0),
                new MancalaBuraco(8 , 6),
                new MancalaBuraco(9 , 6),
                new MancalaBuraco(10 , 6),
                new MancalaBuraco(11 , 6),
                new MancalaBuraco(12 , 6),
                new MancalaBuraco(13 , 6),
                new MancalaBuraco(14 , 0));

        BDDAssertions.then(mancalaJogo.getPartidaJogador()).isNull();
        BDDAssertions.then(mancalaJogo.getBuracos()).isEqualTo(MancalaBuracos);
        BDDAssertions.then(mancalaJogo.getPedrasLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(mancalaJogo.getPedrasLadoDireito()).isEqualTo(0);
    }


    /*
        We first need to run the Mancala Game creation test and use the game id generated to sow the game
     */
    @Test
    public void testManacalaSowPitIndex2 () throws Exception {

        // 1. Run the Mancala Creation Test
        Mockito.when(mancalaClientConfig.getNewMancalaGameUrl()).thenReturn("http://localhost:8080/games");

        WireMock.stubFor(WireMock.post("/games")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson(mancalaCreate))
                ));

        MancalaJogo kalahaGame = mancalaClient.iniciaNovoJogoMancala();

        List<MancalaBuraco> MancalaBuracos = Arrays.asList(
                new MancalaBuraco(1 , 6),
                new MancalaBuraco(2 , 6),
                new MancalaBuraco(3 , 6),
                new MancalaBuraco(4 , 6),
                new MancalaBuraco(5 , 6),
                new MancalaBuraco(6 , 6),
                new MancalaBuraco(7 , 0),
                new MancalaBuraco(8 , 6),
                new MancalaBuraco(9 , 6),
                new MancalaBuraco(10 , 6),
                new MancalaBuraco(11 , 6),
                new MancalaBuraco(12 , 6),
                new MancalaBuraco(13 , 6),
                new MancalaBuraco(14 , 0));

        BDDAssertions.then(kalahaGame.getPartidaJogador()).isNull();
        BDDAssertions.then(kalahaGame.getBuracos()).isEqualTo(MancalaBuracos);
        BDDAssertions.then(kalahaGame.getPedrasLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(kalahaGame.getPedrasLadoDireito()).isEqualTo(0);

        // 2. Run the Mancala Sow test for pit 2

        Mockito.when(mancalaClientConfig.getSowMancalaGameUrl(kalahaGame.getId(), 2)).
                thenReturn("http://localhost:8080/games/"+kalahaGame.getId()+"/pits/2");

        WireMock.stubFor(WireMock.put("/games/"+kalahaGame.getId()+"/pits/2")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson(mancalaSow2))
                ));

        MancalaJogo kalahaGameAfterSowingPit2 = mancalaClient.semeaJogoMancala(kalahaGame.getId(), 2);

        System.out.println(kalahaGameAfterSowingPit2);

        List<MancalaBuraco> newMancalaBuracos = Arrays.asList(
                new MancalaBuraco(1 , 6),
                new MancalaBuraco(2 , 0),
                new MancalaBuraco(3 , 7),
                new MancalaBuraco(4 , 7),
                new MancalaBuraco(5 , 7),
                new MancalaBuraco(6 , 7),
                new MancalaBuraco(7 , 1),
                new MancalaBuraco(8 , 7),
                new MancalaBuraco(9 , 6),
                new MancalaBuraco(10 , 6),
                new MancalaBuraco(11 , 6),
                new MancalaBuraco(12 , 6),
                new MancalaBuraco(13 , 6),
                new MancalaBuraco(14 , 0));

        BDDAssertions.then(kalahaGameAfterSowingPit2.getPartidaJogador()).isEqualTo("PlayerB");
        BDDAssertions.then(kalahaGameAfterSowingPit2.getBuracos()).isEqualTo(newMancalaBuracos);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getPedrasLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getPedrasLadoDireito()).isEqualTo(1);
    }
}

package com.furb.mancala.web.client;

import com.furb.mancala.web.MancalaWebApplication;
import com.furb.mancala.web.model.MancalaJogo;
import com.furb.mancala.web.model.MancalaCova;
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

        List<MancalaCova> MancalaCovas = Arrays.asList(
                new MancalaCova(1 , 6),
                new MancalaCova(2 , 6),
                new MancalaCova(3 , 6),
                new MancalaCova(4 , 6),
                new MancalaCova(5 , 6),
                new MancalaCova(6 , 6),
                new MancalaCova(7 , 0),
                new MancalaCova(8 , 6),
                new MancalaCova(9 , 6),
                new MancalaCova(10 , 6),
                new MancalaCova(11 , 6),
                new MancalaCova(12 , 6),
                new MancalaCova(13 , 6),
                new MancalaCova(14 , 0));

        BDDAssertions.then(mancalaJogo.getPartidaJogador()).isNull();
        BDDAssertions.then(mancalaJogo.getCovas()).isEqualTo(MancalaCovas);
        BDDAssertions.then(mancalaJogo.getSementesLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(mancalaJogo.getSementesLadoDireito()).isEqualTo(0);
    }


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

        List<MancalaCova> MancalaCovas = Arrays.asList(
                new MancalaCova(1 , 6),
                new MancalaCova(2 , 6),
                new MancalaCova(3 , 6),
                new MancalaCova(4 , 6),
                new MancalaCova(5 , 6),
                new MancalaCova(6 , 6),
                new MancalaCova(7 , 0),
                new MancalaCova(8 , 6),
                new MancalaCova(9 , 6),
                new MancalaCova(10 , 6),
                new MancalaCova(11 , 6),
                new MancalaCova(12 , 6),
                new MancalaCova(13 , 6),
                new MancalaCova(14 , 0));

        BDDAssertions.then(kalahaGame.getPartidaJogador()).isNull();
        BDDAssertions.then(kalahaGame.getCovas()).isEqualTo(MancalaCovas);
        BDDAssertions.then(kalahaGame.getSementesLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(kalahaGame.getSementesLadoDireito()).isEqualTo(0);

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

        List<MancalaCova> newMancalaCovas = Arrays.asList(
                new MancalaCova(1 , 6),
                new MancalaCova(2 , 0),
                new MancalaCova(3 , 7),
                new MancalaCova(4 , 7),
                new MancalaCova(5 , 7),
                new MancalaCova(6 , 7),
                new MancalaCova(7 , 1),
                new MancalaCova(8 , 7),
                new MancalaCova(9 , 6),
                new MancalaCova(10 , 6),
                new MancalaCova(11 , 6),
                new MancalaCova(12 , 6),
                new MancalaCova(13 , 6),
                new MancalaCova(14 , 0));

        BDDAssertions.then(kalahaGameAfterSowingPit2.getPartidaJogador()).isEqualTo("PlayerB");
        BDDAssertions.then(kalahaGameAfterSowingPit2.getCovas()).isEqualTo(newMancalaCovas);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getSementesLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getSementesLadoDireito()).isEqualTo(1);
    }
}

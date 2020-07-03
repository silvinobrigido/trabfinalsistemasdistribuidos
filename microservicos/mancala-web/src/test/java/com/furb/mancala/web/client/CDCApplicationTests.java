package com.furb.mancala.web.client;

import com.furb.mancala.web.MancalaWebApplication;
import com.furb.mancala.web.model.MancalaJogo;
import com.furb.mancala.web.model.MancalaCova;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;


@AutoConfigureStubRunner (
        ids = "com.furb.mancalagame:mancala-api:+:8080",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureJson
@SpringBootTest (classes = MancalaWebApplication.class)
@RunWith(SpringRunner.class)
@DirtiesContext
public class CDCApplicationTests {

    @Autowired
    private MancalaClient mancalaClient;

    @Test
    public void testManacalaCreation () throws Exception {

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
    public void testManacalaSowingPitIndex2 () throws Exception {

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

        // 2. Run the Mancala Sow test for pit 2

        MancalaJogo kalahaGameAfterSowingPit2 = mancalaClient.semeaJogoMancala(mancalaJogo.getId(), 2);

        System.out.println(kalahaGameAfterSowingPit2);

        List<MancalaCova> novoMancalaCovas = Arrays.asList(
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
        BDDAssertions.then(kalahaGameAfterSowingPit2.getCovas()).isEqualTo(novoMancalaCovas);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getSementesLadoEsquerdo()).isEqualTo(0);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getSementesLadoDireito()).isEqualTo(1);
    }
}

package com.furb.mancalajogo.service;


import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;

import com.furb.mancalajogo.model.MancalaJogo;

/*
    These tests are written based on 6 stones Mancala game.
 */

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext
public class MancalaSemeacaoServiceTests {

    private static final Integer defaultBuracoStones = 6;

    private MancalaJogo game;

    private MancalaSemeacaoService MancalaSemeacaoService;

    @Before
    public void setupTest (){
        this.game = new MancalaJogo(defaultBuracoStones);
        this.MancalaSemeacaoService = new MancalaSemeacaoService();
    }

    @Test
    public void testGameCreation () {
        Assert.assertNotNull(this.game);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
    }

    @Test
    public void testsemeiaOfSecondBuracoPlayerA(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");
    }

    @Test
    public void testsemeiaOfSecondBuracoPlayerB(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");
    }


    @Test
    public void testsemeiaOfSixthBuracoPlayerA(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 6);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");
    }

    @Test
    public void testWrongPartidaByPlayerA(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 6);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        // This is a wrong Partida therefore the output remains the same as well as player Partida
        this.game = MancalaSemeacaoService.semeia(this.game, 1);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");
    }

    @Test
    public void testsemeiaOfSixthBuracoPlayerB(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 6);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 13);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");
    }

    @Test
    public void testWrongPartidaByPlayerB(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 6);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 13);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        // This is a wrong Partida therefore the output remains the same as well as player Partida
        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");
    }

    @Test
    public void testsemeiaOfCollectingOppositeBuracoStoneByPlayerA(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 6);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 13);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 3);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:2, 10:9, 11:9, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:0, 10:10, 11:10, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:0, 3:0, 4:9, 5:9, 6:2, 7:14, 8:10, 9:0, 10:10, 11:0, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");
    }

    @Test
    public void testsemeiaOfCollectingOppositeBuracoStoneByPlayerB(){
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 6);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 13);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 3);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:2, 10:9, 11:9, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:0, 10:10, 11:10, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:8, 2:0, 3:0, 4:9, 5:9, 6:2, 7:14, 8:10, 9:0, 10:10, 11:0, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 8);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:9, 2:1, 3:1, 4:10, 5:9, 6:2, 7:14, 8:0, 9:1, 10:11, 11:1, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 1);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:0, 2:2, 3:2, 4:11, 5:10, 6:3, 7:15, 8:1, 9:2, 10:12, 11:1, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 9);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:0, 2:2, 3:2, 4:11, 5:10, 6:3, 7:15, 8:1, 9:0, 10:13, 11:2, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");

        this.game = MancalaSemeacaoService.semeia(this.game, 2);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:0, 2:0, 3:3, 4:12, 5:10, 6:3, 7:15, 8:1, 9:0, 10:13, 11:2, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("B");

        this.game = MancalaSemeacaoService.semeia(this.game, 8);
        Assertions.assertThat(this.game.getBuracos().toString()).isEqualTo("[1:0, 2:0, 3:3, 4:12, 5:0, 6:3, 7:15, 8:0, 9:0, 10:13, 11:2, 12:9, 13:1, 14:14]");
        Assertions.assertThat(this.game.getPartidaJogador().getPartida()).isEqualTo("A");
    }
}




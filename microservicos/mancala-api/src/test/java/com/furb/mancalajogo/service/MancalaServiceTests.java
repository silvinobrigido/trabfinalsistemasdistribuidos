package com.furb.mancalajogo.service;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.furb.mancalajogo.model.MancalaBuraco;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.repository.MancalaRepositorioJogo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@RunWith(SpringRunner.class)
public class MancalaServiceTests {

    @MockBean
    private MancalaRepositorioJogo kalahaGameRepository;

    @Autowired
    private MancalaService kalahaGameService;

    @Test
    public void testCreatingNewlyGameInstance () throws Exception {
        MancalaJogo gameInstance = kalahaGameService.criarJogo(6);

        BDDAssertions.then(gameInstance.getPartidaJogador()).isNull();
        BDDAssertions.then (gameInstance.getBuracos()).size().isEqualTo(14);
        BDDAssertions.then (gameInstance.getBuraco(1).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(2).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(3).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(4).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(5).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(6).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(7).getPedras()).isEqualTo(0);
        BDDAssertions.then (gameInstance.getBuraco(8).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(9).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(10).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(11).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(12).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(13).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(14).getPedras()).isEqualTo(0);
    }

    @Test
    public void testLoadingGameInstanceFromRepository () throws Exception {

    	MancalaJogo expectedGame = new MancalaJogo("5d414dcd24e4990006e7c9d3" , 6);
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
        expectedGame.setBuracos(MancalaBuracos);
        Optional<MancalaJogo> gameOptional= Optional.of(expectedGame);
        Mockito.when(kalahaGameRepository.findById("5d414dcd24e4990006e7c9d3")).thenReturn(gameOptional);

        MancalaJogo gameInstance = kalahaGameService.loadGame("5d414dcd24e4990006e7c9d3");

        BDDAssertions.then(gameInstance.getId()).isEqualTo("5d414dcd24e4990006e7c9d3");
        BDDAssertions.then(gameInstance.getPartidaJogador()).isNull();
        BDDAssertions.then (gameInstance.getBuracos()).size().isEqualTo(14);
        BDDAssertions.then (gameInstance.getBuraco(1).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(2).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(3).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(4).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(5).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(6).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(7).getPedras()).isEqualTo(0);
        BDDAssertions.then (gameInstance.getBuraco(8).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(9).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(10).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(11).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(12).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(13).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(14).getPedras()).isEqualTo(0);
    }

    @Test
    public void testUpdatingGameInstanceIntoRepository () throws Exception {

    	MancalaJogo expectedGame = new MancalaJogo("5d414dcd24e4990006e7c9d3" , 6);
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
        expectedGame.setBuracos(MancalaBuracos);
        Mockito.when(kalahaGameRepository.save(expectedGame)).thenReturn(expectedGame);

        MancalaJogo gameInstance = kalahaGameService.updateGame(expectedGame);

        BDDAssertions.then(gameInstance.getId()).isEqualTo("5d414dcd24e4990006e7c9d3");
        BDDAssertions.then(gameInstance.getPartidaJogador()).isNull();
        BDDAssertions.then (gameInstance.getBuracos()).size().isEqualTo(14);
        BDDAssertions.then (gameInstance.getBuraco(1).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(2).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(3).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(4).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(5).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(6).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(7).getPedras()).isEqualTo(0);
        BDDAssertions.then (gameInstance.getBuraco(8).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(9).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(10).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(11).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(12).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(13).getPedras()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getBuraco(14).getPedras()).isEqualTo(0);
    }

}

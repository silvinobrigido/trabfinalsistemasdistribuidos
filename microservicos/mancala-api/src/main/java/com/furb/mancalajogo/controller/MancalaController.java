package com.furb.mancalajogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furb.mancalajogo.constantes.MancalaConstantes;
import com.furb.mancalajogo.excecoes.MancalaApiException;
import com.furb.mancalajogo.model.MancalaJogo;
import com.furb.mancalajogo.service.MancalaSemeacaoService;
import com.furb.mancalajogo.service.MancalaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/jogo")
@Api(value = "Mancala game API. Set of endpoints for Creating and Sowing the Game")
public class MancalaController {

    @Autowired
    private MancalaService mancalaService;

    @Autowired
    private MancalaSemeacaoService mancalaSowingService;

    @Value("${mancala.pit.stones}")
    private Integer pitStones;

    @PostMapping
    @ApiOperation(value = "Endpoint for creating new Mancala game instance. It returns a MancalaJogo object with unique GameId used for sowing the game",
            produces = "Application/JSON", response = MancalaJogo.class, httpMethod = "POST")
    public ResponseEntity<MancalaJogo> createGame() throws Exception {

        log.info("Invoking create() endpoint... ");

        MancalaJogo jogo = mancalaService.criarJogo(pitStones);

        log.info("Game instance created. Id=" + jogo.getId());

        log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jogo));

        mancalaService.updateGame(jogo);

        return ResponseEntity.ok(jogo);
    }

    @PutMapping(value = "{gameId}/pits/{pitId}")
    @ApiOperation(value = "Endpoint for sowing the game. It keeps the history of the Game instance for consecutive requests. ",
            produces = "Application/JSON", response = MancalaJogo.class, httpMethod = "PUT")
    public ResponseEntity<MancalaJogo> semeaJogo(
            @ApiParam(value = "The id of game created by calling createGame() method. It can't be empty or null", required = true)
            @PathVariable(value = "gameId") String gameId,
            @PathVariable(value = "pitId") Integer pitId) throws Exception {

        log.info("Invoking sow() endpoint. GameId: " + gameId + "  , pit Index: " + pitId);

        if (pitId == null || pitId < 1 || pitId >= MancalaConstantes.leftPitHouseId || pitId == MancalaConstantes.rightPitHouseId)
            throw new MancalaApiException("Invalid pit Index!. It should be between 1..6 or 8..13");

        MancalaJogo MancalaJogo = mancalaService.loadGame(gameId);

        MancalaJogo = mancalaSowingService.semeia(MancalaJogo, pitId);

        mancalaService.updateGame(MancalaJogo);

        log.info("sow is called for Game id:" + gameId + " , pitIndex:" + pitId);

        log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(MancalaJogo));

        return ResponseEntity.ok(MancalaJogo);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Endpoint for returning the latest status of the Game",
            produces = "Application/JSON", response = MancalaJogo.class, httpMethod = "GET")
    public ResponseEntity<MancalaJogo> gameStatus(
            @ApiParam(value = "The id of game created by calling createGame() method. It's an String e.g. 5d34968590fcbd35b086bc21. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") String gameId) throws Exception {

        return ResponseEntity.ok(mancalaService.loadGame(gameId));
    }


}
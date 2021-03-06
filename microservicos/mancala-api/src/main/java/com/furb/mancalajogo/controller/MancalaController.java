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
@Api(value = "")
public class MancalaController {

    @Autowired	
    private MancalaService mancalaService;

    @Autowired
    private MancalaSemeacaoService mancalaSowingService;

    @Value("${mancala.pit.stones}")
    private Integer pitStones;

    @PostMapping
    @ApiOperation(value = "",
            produces = "Application/JSON", response = MancalaJogo.class, httpMethod = "POST")
    public ResponseEntity<MancalaJogo> createGame() throws Exception {

    

        MancalaJogo jogo = mancalaService.criarJogo(pitStones);

       log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jogo));

        mancalaService.updateGame(jogo);

        return ResponseEntity.ok(jogo);
    }

    @PutMapping(value = "{gameId}/covas/{covaId}")
    @ApiOperation(value = " ",
            produces = "Application/JSON", response = MancalaJogo.class, httpMethod = "PUT")
    public ResponseEntity<MancalaJogo> semeaJogo(
            @ApiParam(value = "", required = true)
            @PathVariable(value = "jogoId") String gameId,
            @PathVariable(value = "covaId") Integer covaId) throws Exception {
        

        if (covaId == null || covaId < 1 || covaId >= MancalaConstantes.covaEsquerdaId || covaId == MancalaConstantes.covaDireitaId)
            throw new MancalaApiException("Erro!!!");

        MancalaJogo MancalaJogo = mancalaService.loadGame(gameId);

        MancalaJogo = mancalaSowingService.semeia(MancalaJogo, covaId);

        mancalaService.updateGame(MancalaJogo);

        log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(MancalaJogo));

        return ResponseEntity.ok(MancalaJogo);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Endpoint for returning the latest status of the Game",
            produces = "Application/JSON", response = MancalaJogo.class, httpMethod = "GET")
    public ResponseEntity<MancalaJogo> gameStatus(
            @ApiParam(value = "",
                    required = true)
            @PathVariable(value = "id") String gameId) throws Exception {

        return ResponseEntity.ok(mancalaService.loadGame(gameId));
    }


}

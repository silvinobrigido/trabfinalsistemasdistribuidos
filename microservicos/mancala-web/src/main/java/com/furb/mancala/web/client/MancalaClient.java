package com.furb.mancala.web.client;

import com.furb.mancala.web.model.MancalaJogo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Slf4j
public class MancalaClient {

    private RestTemplate restTemplate;

    @Autowired
    private MancalaClientConfig mancalaClientConfig;

    public MancalaClient(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MancalaJogo iniciaNovoJogoMancala() throws Exception {

        String url = mancalaClientConfig.getNewMancalaGameUrl();

        log.info("calling:" + url);

        ResponseEntity<MancalaJogo> gameResponse = this.restTemplate.postForEntity(url, null, MancalaJogo.class);

        log.info("response: " + new ObjectMapper().writerWithDefaultPrettyPrinter().
                writeValueAsString(gameResponse.getBody()));

        return gameResponse.getBody();
    }

    public MancalaJogo semeaJogoMancala(String gameId, Integer pitIndex) throws Exception {

        String url = mancalaClientConfig.getSowMancalaGameUrl(gameId, pitIndex);

        log.info("calling: " + url);

        ResponseEntity<MancalaJogo> response = restTemplate.exchange(url, HttpMethod.PUT, null, MancalaJogo.class);

        log.info("response: " + new ObjectMapper().writerWithDefaultPrettyPrinter().
                writeValueAsString(response.getBody()));

        return response.getBody();
    }
}

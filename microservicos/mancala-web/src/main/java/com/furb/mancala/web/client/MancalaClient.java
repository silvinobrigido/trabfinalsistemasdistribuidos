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

        String url = mancalaClientConfig.getMancalaURL();

        log.info("Acesso:" + url);

        ResponseEntity<MancalaJogo> resposta = this.restTemplate.postForEntity(url, null, MancalaJogo.class);

        log.info("Resposta: " + new ObjectMapper().writerWithDefaultPrettyPrinter().
                writeValueAsString(resposta.getBody()));

        return resposta.getBody();
    }

    public MancalaJogo semeaJogoMancala(String codJogo, Integer indCova) throws Exception {

        String url = mancalaClientConfig.getSemeacaoMancalaURL(codJogo, indCova);

        log.info("Acesso: " + url);

        ResponseEntity<MancalaJogo> resposta = restTemplate.exchange(url, HttpMethod.PUT, null, MancalaJogo.class);

        log.info("Resposta: " + new ObjectMapper().writerWithDefaultPrettyPrinter().
                writeValueAsString(resposta.getBody()));

        return resposta.getBody();
    }
}

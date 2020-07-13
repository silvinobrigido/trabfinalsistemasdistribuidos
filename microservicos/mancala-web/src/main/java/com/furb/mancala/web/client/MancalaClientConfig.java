package com.furb.mancala.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

@Component
public class MancalaClientConfig {
    
	@Autowired
    private LoadBalancerClient loadBalancer;

    @Value("${mancala.api.service.id}")
    private final String apiServiceId = "mancala-api";

    @Autowired
    public void setLoadBalancer(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public String getMancalaURL(){
        ServiceInstance instance = this.loadBalancer.choose(apiServiceId);

        String url = String.format("http://%s:%s/jogo", instance.getHost(), instance.getPort());

        return url;
    }

    public String getSemeacaoMancalaURL(String gameId, Integer pitIndex){
        ServiceInstance instance = this.loadBalancer.choose(apiServiceId);

        String url = String.format("http://%s:%s/jogo/%s/covas/%s", instance.getHost(), instance.getPort(), gameId, pitIndex);

        return url;
    }
}

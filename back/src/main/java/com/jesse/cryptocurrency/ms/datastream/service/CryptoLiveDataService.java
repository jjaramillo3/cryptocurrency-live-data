package com.jesse.cryptocurrency.ms.datastream.service;


import com.jesse.cryptocurrency.ms.datastream.model.CurrencyRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@Configuration
public class CryptoLiveDataService {

    public static final String SERVICE_URI = "/exchange_rates";

    @Autowired
    private WebClient coinGeckoWebClient;


    public Flux<CurrencyRateResponse> getLiveData() {
        return coinGeckoWebClient.get()
                .uri(SERVICE_URI)
                .retrieve()
                .bodyToFlux(CurrencyRateResponse.class);
    }

}

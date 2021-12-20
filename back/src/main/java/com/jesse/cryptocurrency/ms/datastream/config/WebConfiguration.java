package com.jesse.cryptocurrency.ms.datastream.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import static org.springframework.http.MediaType.*;
import static org.springframework.http.HttpHeaders.*;

@Configuration
public class WebConfiguration {

    public static final String BASE_URL = "https://api.coingecko.com/api/v3";

    @Bean
    public Scheduler subscriberSchedule() {
        return Schedulers.boundedElastic();
    }

    @Bean(name = "coinGeckoWebClient")
    public WebClient coinGeckoWebClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set(ACCEPT, APPLICATION_JSON_VALUE);
                    httpHeaders.set(CONNECTION, "keep-alive");
                })
                .build();
    }

}

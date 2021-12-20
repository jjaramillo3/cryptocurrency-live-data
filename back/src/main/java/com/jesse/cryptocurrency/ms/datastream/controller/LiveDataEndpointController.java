package com.jesse.cryptocurrency.ms.datastream.controller;


import com.jesse.cryptocurrency.ms.datastream.model.CurrencyRateResponse;
import com.jesse.cryptocurrency.ms.datastream.service.CryptoLiveDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RestController
public class LiveDataEndpointController {

    @Autowired
    private CryptoLiveDataService streamingService;

    @Autowired
    private Scheduler subscriberScheduler;

    @GetMapping(value = "/liveData", produces = APPLICATION_JSON_VALUE)
    public Flux<CurrencyRateResponse> subscribe() {
        log.info("Request made to fetch live data");

        return Flux.zip(streamingService.getLiveData(), Flux.interval(Duration.ofSeconds(1)),
                (event,interval) -> {
                    log.info("Retrieved data: {}", event);
                    return event;
                }).delayElements(Duration.ofSeconds(1), subscriberScheduler);

    }

}

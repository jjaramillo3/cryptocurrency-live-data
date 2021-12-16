package com.jesse.cryptocurrency.ms.datastream;

import com.jesse.cryptocurrency.ms.datastream.model.CurrencyRateResponse;
import com.jesse.cryptocurrency.ms.datastream.service.CryptoLiveDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CryptocurrencyLiveDataMicroserviceApplicationTests {

	private static final String CURRENCY_EXCHANGED_TO = "US Dollar";

	@Autowired
	private CryptoLiveDataService streamingService;

	CurrencyRateResponse currencyRateResponse;

	@BeforeEach
	void beforeEach() {
		currencyRateResponse = streamingService.getLiveData().blockFirst(Duration.ofMillis(1_000));
	}

	@Test
	void testExpectedFields() {
		assertAll(() -> assertEquals(CURRENCY_EXCHANGED_TO,
				currencyRateResponse.getRates().getUsd().getName()),
				() -> assertNull(currencyRateResponse.getErrorMessage()));
	}

}

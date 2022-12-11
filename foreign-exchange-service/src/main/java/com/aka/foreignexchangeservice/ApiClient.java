package com.aka.foreignexchangeservice;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

import com.aka.foreignexchangeservice.model.ForeignCurrency;
import com.aka.foreignexchangeservice.repository.ForeignCurrencyDao;

import reactor.core.publisher.Mono;

@SuppressWarnings("unchecked")
@EnableAsync
@Configuration
@EnableScheduling
public class ApiClient {
	private static final String FIXER_BASE_URL = "https://api.apilayer.com/fixer/latest?symbols=try";
	private static final String CURRENCY_DATA_URL = "https://api.apilayer.com/currency_data/live?";
	// Enter your API key here
	private static final String API_KEY = config.myApiKey;

	WebClient client = WebClient.create();
	
	@Autowired
	private ForeignCurrencyDao dao;

	@Async
	@Scheduled(fixedRate = 3600000)
    public void getEuroTryFixerApi() {
		Mono<Map<String, Object>> response = client.get()
			.uri(FIXER_BASE_URL + "&base=eur")
			.accept(MediaType.APPLICATION_JSON)
			.header("apikey", API_KEY)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
		
		// Map the response to a ForeignCurrency instance
		Mono<ForeignCurrency> currency = response.map(result -> {
			ForeignCurrency foreignCurrency = new ForeignCurrency();
			foreignCurrency.setBase((String) result.get("base"));
			foreignCurrency.setRates((Map<String, Double>) result.get("rates"));
			Integer timestampint = (Integer) result.get("timestamp");
			long timestamp = Long.valueOf(timestampint.longValue());
			Instant instant = Instant.ofEpochSecond(timestamp);
			// add +3 hours to the timestamp to get the correct date in Turkey (GMT+3)
			instant = instant.plus( Duration.ofHours(3) );
			foreignCurrency.setDate(Date.from(instant));
			return foreignCurrency;
		});

		ForeignCurrency theCurrency = currency.block();
		theCurrency.setApiSource("fixerApi");
		dao.save(theCurrency);

    }
	@Async
	@Scheduled(fixedRate = 3600000)
	public void getUsdTryFixerApi() {
		Mono<Map<String, Object>> response = client.get()
			.uri(FIXER_BASE_URL + "&base=usd")
			.accept(MediaType.APPLICATION_JSON)
			.header("apikey", API_KEY)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
		
		// Map the response to a ForeignCurrency instance
		Mono<ForeignCurrency> currency = response.map(result -> {
			ForeignCurrency foreignCurrency = new ForeignCurrency();
			foreignCurrency.setBase((String) result.get("base"));
			foreignCurrency.setRates((Map<String, Double>) result.get("rates"));
			Integer timestampint = (Integer) result.get("timestamp");
			long timestamp = Long.valueOf(timestampint.longValue());
			Instant instant = Instant.ofEpochSecond(timestamp);
			// add +3 hours to the timestamp to get the correct date in Turkey (GMT+3)
			instant = instant.plus( Duration.ofHours(3) );
			foreignCurrency.setDate(Date.from(instant));
			return foreignCurrency;
		});

		ForeignCurrency theCurrency = currency.block();
		theCurrency.setApiSource("fixerApi");
		dao.save(theCurrency);

    }

	@Async
	@Scheduled(fixedRate = 3600000)
	public void getEuroTryCurrencyDataApi() {
		Mono<Map<String, Object>> response = client.get()
			.uri(CURRENCY_DATA_URL + "source=EUR&currencies=TRY")
			.accept(MediaType.APPLICATION_JSON)
			.header("apikey", API_KEY)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
		
		// Map the response to a ForeignCurrency instance
		Mono<ForeignCurrency> currency = response.map(result -> {
			ForeignCurrency foreignCurrency = new ForeignCurrency();
			foreignCurrency.setBase((String) result.get("source"));
			foreignCurrency.setRates((Map<String, Double>) result.get("quotes"));
			Integer timestampint = (Integer) result.get("timestamp");
			long timestamp = Long.valueOf(timestampint.longValue());
			Instant instant = Instant.ofEpochSecond(timestamp);
			// add +3 hours to the timestamp to get the correct date in Turkey (GMT+3)
			instant = instant.plus( Duration.ofHours(3) );
			foreignCurrency.setDate(Date.from(instant));
			return foreignCurrency;
		});

		ForeignCurrency theCurrency = currency.block();

		theCurrency.setApiSource("currencyDataApi");

		dao.save(theCurrency);
    }

	@Async
	@Scheduled(fixedRate = 3600000)
	public void getUsdTryCurrencyDataApi() {
		Mono<Map<String, Object>> response = client.get()
			.uri(CURRENCY_DATA_URL + "source=USD&currencies=TRY")
			.accept(MediaType.APPLICATION_JSON)
			.header("apikey", API_KEY)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
		
		// Map the response to a ForeignCurrency instance
		Mono<ForeignCurrency> currency = response.map(result -> {
			ForeignCurrency foreignCurrency = new ForeignCurrency();
			foreignCurrency.setBase((String) result.get("source"));
			foreignCurrency.setRates((Map<String, Double>) result.get("quotes"));
			Integer timestampint = (Integer) result.get("timestamp");
			long timestamp = Long.valueOf(timestampint.longValue());
			Instant instant = Instant.ofEpochSecond(timestamp);
			// add +3 hours to the timestamp to get the correct date in Turkey (GMT+3)
			instant = instant.plus( Duration.ofHours(3) );
			foreignCurrency.setDate(Date.from(instant));
			return foreignCurrency;
		});

		ForeignCurrency theCurrency = currency.block();
		theCurrency.setApiSource("currencyDataApi");

		dao.save(theCurrency);

    }
}

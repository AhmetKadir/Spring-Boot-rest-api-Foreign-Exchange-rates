package com.aka.foreignexchangeservice;
import java.text.SimpleDateFormat;
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
import com.aka.foreignexchangeservice.repository.ForeignCurrencyRepository;

import reactor.core.publisher.Mono;

//@SuppressWarnings("unchecked")
@EnableAsync
@Configuration
@EnableScheduling
public class ApiClient {
	private static final String FIXER_BASE_URL = "https://api.apilayer.com/fixer/latest?symbols=try";
	private static final String CURRENCY_DATA_URL = "https://api.apilayer.com/currency_data/live?";
	private static final String FIXER_EUR_URL = "&base=eur";
	private static final String FIXER_USD_URL = "&base=usd";
	private static final String CURRENCY_DATA_EUR_URL = "source=EUR&currencies=TRY";
	private static final String CURRENCY_DATA_USD_URL = "source=USD&currencies=TRY";
	// Enter your API key here
	private static final String API_KEY = config.myApiKey;

	WebClient client = WebClient.create();
	
	@Autowired
	private ForeignCurrencyRepository foreignCurrencyRepository;

	@Async
	@Scheduled(fixedRate = 3600000)
    public void getEuroTryFixerApi() {
		updateCurrency(FIXER_BASE_URL, FIXER_EUR_URL, "fixerApi", "base", "rates");
	}

    
	@Async
	@Scheduled(fixedRate = 3600000)
	public void getUsdTryFixerApi() {
		updateCurrency(FIXER_BASE_URL, FIXER_USD_URL, "fixerApi", "base", "rates");
    }

	@Async
	@Scheduled(fixedRate = 3600000)
	public void getEuroTryCurrencyDataApi() {
		updateCurrency(CURRENCY_DATA_URL, CURRENCY_DATA_EUR_URL, "currencyDataApi", "source", "quotes");
    }

	@Async
	@Scheduled(fixedRate = 3600000)
	public void getUsdTryCurrencyDataApi() {
		updateCurrency(CURRENCY_DATA_URL, CURRENCY_DATA_USD_URL, "currencyDataApi", "source", "quotes");
    }

	public void updateCurrency(String baseUrl, String url, String apiSource, String base, String rates) {
		Mono<Map<String, Object>> response = client.get()
			.uri(baseUrl + url)
			.accept(MediaType.APPLICATION_JSON)
			.header("apikey", API_KEY)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
		
		// Map the response to a ForeignCurrency instance
		Mono<ForeignCurrency> currency = response.map(result -> {
			ForeignCurrency foreignCurrency = new ForeignCurrency();
			foreignCurrency.setBase((String) result.get(base));
			foreignCurrency.setRates((Map<String, Double>) result.get(rates));
			Integer timestampint = (Integer) result.get("timestamp");

			// get Day and Time from the timestamp in the response and set it to the ForeignCurrency instance 
			String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date (timestampint*1000L));
			String time = new SimpleDateFormat("HH").format(new Date (timestampint*1000L));
			foreignCurrency.setDay(day);
			foreignCurrency.setTime(time);
			return foreignCurrency;
		});

		ForeignCurrency theCurrency = currency.block();
		theCurrency.setApiSource(apiSource);

		foreignCurrencyRepository.save(theCurrency);
    }
}

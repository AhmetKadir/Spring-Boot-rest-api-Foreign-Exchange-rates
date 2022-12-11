package com.aka.foreignexchangeservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aka.foreignexchangeservice.model.ForeignCurrency;


@Service
public class ApiClient {
	private static final String API_BASE_URL = "https://api.apilayer.com/fixer/latest?symbols=try&base=eur";
	// Enter your API key here
	private static final String API_KEY = config.myApiKey;

    public ForeignCurrency getForeignCurrency() {
		WebClient client = WebClient.create();

		ForeignCurrency currency =  client
				.get()
				.uri(API_BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.header("apikey", API_KEY)
				.retrieve()
				.bodyToMono(ForeignCurrency.class)
				.block();

		return currency;

		
		/*ForeignCurrency cur = new ForeignCurrency();

		return cur;*/

    }
}

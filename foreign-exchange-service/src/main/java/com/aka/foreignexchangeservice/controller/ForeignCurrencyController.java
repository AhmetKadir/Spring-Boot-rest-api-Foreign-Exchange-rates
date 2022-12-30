package com.aka.foreignexchangeservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aka.foreignexchangeservice.model.ForeignCurrency;
import com.aka.foreignexchangeservice.repository.ForeignCurrencyRepository;


@RestController
@RequestMapping("/api/currencies")
public class ForeignCurrencyController {

	@Autowired 
	ForeignCurrencyRepository repository;

	@GetMapping()
	public List<ForeignCurrency> getCurrency() {
		return repository.findAll();
	}

	@GetMapping("apiSource/{apiSource}")
	public List<ForeignCurrency> getByApiSource(@PathVariable String apiSource) {
		return repository.findByApiSource(apiSource);
	}

	@GetMapping("showCurrencies")
	public List<ForeignCurrency> showCurrencies(@RequestParam(required = false) String baseCurrency,
			@RequestParam(required = false) String apiSource, @RequestParam(required = false) String day,
			@RequestParam(required = false) String time) {
		
		// Initialize an empty list of ForeignCurrency objects to store the results
		List<ForeignCurrency> currencies = new ArrayList<>();
		
		// Retrieve the currencies with the specified base currency
		if (baseCurrency != null) {
			currencies = repository.findByBase(baseCurrency);
		}
		else{
			currencies = repository.findAll();
		}
		
		// Filter the currencies by the specified api source
		if (apiSource != null) {
			currencies = currencies.stream()
					.filter(currency -> currency.getApiSource().equals(apiSource))
					.collect(Collectors.toList());
		}
		
		// Filter the currencies by the specified day
		if (day != null) {
			currencies = currencies.stream()
					.filter(currency -> currency.getDay().equals(day))
					.collect(Collectors.toList());
		}
		
		// Filter the currencies by the specified time
		if (time != null) {
			currencies = currencies.stream()
					.filter(currency -> currency.getTime().equals(time))
					.collect(Collectors.toList());
		}
		
		// Return the filtered list of currencies
		return currencies;
	}
	

	@GetMapping("currency/{baseCurrency}")
	public List<ForeignCurrency> getByBaseCurrency(@PathVariable String baseCurrency) {
		return repository.findByBase(baseCurrency);
	}
	
	@GetMapping("date/{day}")
	public List<ForeignCurrency> getByDay(@PathVariable String day) {
		return repository.findByDay(day);
	}

	@GetMapping("date/{day}/{time}")
	public List<ForeignCurrency> getByDayAndTime(@PathVariable String day, @PathVariable String time) {
		return repository.findByDayAndTime(day, time);
	}

	@GetMapping("date/{day}/{time1}/{time2}")
	public List<ForeignCurrency> getByDayAndTimeRange(@PathVariable String day, @PathVariable String time1, @PathVariable String time2) {
		return repository.findByDayAndTimeBetween(day, time1, time2);
	}

}

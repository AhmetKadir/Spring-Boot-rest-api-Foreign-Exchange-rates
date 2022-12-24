package com.aka.foreignexchangeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("base/{baseCurrency}")
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

}

package com.aka.foreignexchangeservice.repository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.aka.foreignexchangeservice.model.ForeignCurrency;

@Service
public class ForeignCurrencyDao {

	@Autowired
	private ForeignCurrencyRepository repository;

	public ForeignCurrency save(ForeignCurrency currency){
		return repository.save(currency);
	}
}

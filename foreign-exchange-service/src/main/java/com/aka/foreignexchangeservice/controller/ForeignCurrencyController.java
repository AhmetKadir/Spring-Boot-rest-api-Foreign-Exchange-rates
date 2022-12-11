package com.aka.foreignexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aka.foreignexchangeservice.ApiClient;
import com.aka.foreignexchangeservice.model.ForeignCurrency;
import com.aka.foreignexchangeservice.repository.ForeignCurrencyDao;
// import com.aka.foreignexchangeservice.repository.ForeignCurrencyRepository;


@RestController
@RequestMapping("/api")
public class ForeignCurrencyController {

	@Autowired
	private ForeignCurrencyDao dao;

}

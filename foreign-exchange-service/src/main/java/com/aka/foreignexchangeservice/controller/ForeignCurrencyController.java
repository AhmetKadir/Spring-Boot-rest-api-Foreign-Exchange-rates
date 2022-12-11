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
    private ApiClient apiClient;

	/*@Autowired
	private ForeignCurrencyRepository repository;*/

	@Autowired
	private ForeignCurrencyDao dao;

	//private static final Logger logger = LoggerFactory.getLogger(ForeignCurrencyController.class);

	@GetMapping("/euro")
    public ForeignCurrency getForeignCurrency() {
        ForeignCurrency foreignCurrency = apiClient.getForeignCurrency();
		System.out.println(foreignCurrency.toString());
		return dao.save(foreignCurrency);
    }

	/*public Mono<ServerResponse> getForeignCurrency(ServerRequest request) { 
		Mono<ForeignCurrency> currency = request.bodyToMono(ForeignCurrency.class);
		return ServerResponse.ok().build(repository.save(currency));
	}*/
}

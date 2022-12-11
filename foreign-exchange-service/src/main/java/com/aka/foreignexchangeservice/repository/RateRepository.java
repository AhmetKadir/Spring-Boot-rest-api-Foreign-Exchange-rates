package com.aka.foreignexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aka.foreignexchangeservice.model.ForeignCurrency;

@RepositoryRestResource(path = "rates")
public interface RateRepository extends JpaRepository<ForeignCurrency, Integer>{
	
}
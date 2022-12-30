package com.aka.foreignexchangeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aka.foreignexchangeservice.model.ForeignCurrency;

//@RepositoryRestResource(path = "foreigncurrencies")
public interface ForeignCurrencyRepository extends JpaRepository<ForeignCurrency, Integer>{
	List<ForeignCurrency> findByApiSource(String apiSource);
	List<ForeignCurrency> findByBase(String base);
	List<ForeignCurrency> findByDay(String day);
	List<ForeignCurrency> findByTime(String time);
	List<ForeignCurrency> findByDayAndTime(String day, String time);
	List<ForeignCurrency> findByDayAndTimeBetween(String day, String time1, String time2);
}

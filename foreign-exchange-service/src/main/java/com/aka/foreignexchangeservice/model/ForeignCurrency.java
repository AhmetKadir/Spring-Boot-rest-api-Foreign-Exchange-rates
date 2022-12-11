package com.aka.foreignexchangeservice.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.*;

@Entity
@Table(name="foreign_currency")
public class ForeignCurrency{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

    @Column(name="base", length = 3)
	private String base;

    @Column(name="date")
    private	Date date;

    @Column(name="api_source")
    private String apiSource;

    @ElementCollection
    @CollectionTable(name = "rates", 
      joinColumns = {@JoinColumn(name = "foreign_currency_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "currency_code")
    @Column(name = "exchange_rate")
    private Map<String, Double> rates;


    public ForeignCurrency() {
    }


    /*public int getId() {
		return id;
	}*/


    public void setId(int id) {
		this.id = id;
	}

    public ForeignCurrency(String base, Date date, String apiSource, Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.apiSource = apiSource;
        this.rates = rates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getApiSource() {
        return apiSource;
    }

    public void setApiSource(String apiSource) {
        this.apiSource = apiSource;
    }

    @Override
    public String toString() {
        return "ForeignCurrency [id=" + id + ", base=" + base + ", date=" + date + ", apiSource=" + apiSource
                + ", rates=" + rates + "]";
    }


	/*
	"success": true,
    "timestamp": 1519296206,
    "base": "EUR",
    "date": "2022-11-25",
    "rates": 
        "AUD": 1.566015,
        "CAD": 1.560132,
        "CHF": 1.154727,
        "CNY": 7.827874,
        "GBP": 0.882047,
        "JPY": 132.360679,
        "USD": 1.23396,
	*/
}

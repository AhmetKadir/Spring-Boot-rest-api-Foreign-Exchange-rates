package com.aka.foreignexchangeservice.model;

import java.util.Map;

import javax.persistence.*;

import org.hibernate.annotations.Type;

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
    private	String date;

    @ElementCollection
    @CollectionTable(name = "rates", 
      joinColumns = {@JoinColumn(name = "foreign_currency_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "currency_code")
    @Column(name = "exchange_rate")
    private Map<String, Double> rates;
	
    @Column(name="time_stamp")
	private Long timestamp;

    @Column(name="success")
    private boolean success;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ForeignCurrency() {
    }


    /*public int getId() {
		return id;
	}*/

	public ForeignCurrency(String base, String date, Map<String, Double> rates, Long timestamp,
            boolean success) {
        this.base = base;
        this.date = date;
        this.rates = rates;
        this.timestamp = timestamp;
        this.success = success;
    }

    public void setId(int id) {
		this.id = id;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ForeignCurrency [base=" + base + ", date=" + date + ", rates=" + rates + ", timestamp=" + timestamp
                + ", success=" + success + "]";
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

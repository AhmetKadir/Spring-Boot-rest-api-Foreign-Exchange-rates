package com.aka.foreignexchangeservice.model;

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

    @Column(name="day")
    private	String day;

    @Column(name="time")
    private String time;

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

    public ForeignCurrency(String base, String day, String hour, String apiSource, Map<String, Double> rates) {
        this.base = base;
        this.day = day;
        this.time = hour;
        this.apiSource = apiSource;
        this.rates = rates;
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

    public String getApiSource() {
        return apiSource;
    }

    public void setApiSource(String apiSource) {
        this.apiSource = apiSource;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String hour) {
        this.time = hour;
    }

    @Override
    public String toString() {
        return "ForeignCurrency{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + time + '\'' +
                ", apiSource='" + apiSource + '\'' +
                ", rates=" + rates +
                '}';
    }


}

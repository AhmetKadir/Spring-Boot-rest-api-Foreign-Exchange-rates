# Spring-boot-rest-api-Foreign-Exchange
The goal of this project is to create a system that can automatically retrieve current foreign exchange rates from two different APIs (here fixer api and currency data api are used) on an hourly basis. The system should save the data into a database, and provide features for filtering and sorting the data by currency, date, and API. For example, users should be able to easily retrieve the exchange rate for a specific currency on a specific date, or compare the rates from different APIs for a given time period.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/AhmetKadir/spring-boot-rest-api-foreign-exchange-rates.git
```

**2. Create Mysql database**
```bash
Need to create mysql database 
```
**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8075>

## Explore Rest APIs
### Currencies
| Method | Url | Description |
| ------ | --- | ----------- |
| GET    | /api/currencies | Get all currencies |
| GET    | /api/currencies/apiSource/{apiSource} | Get currencies by api source |
| GET    | /api/currencies/base/{baseCurrency} | Get currencies by base currency (EUR, USD)|
| GET    | /api/currencies/date/{day} | Get currencies by day |
| GET    | /api/currencies/date/{day}/{time} | Get currencies by day and time |


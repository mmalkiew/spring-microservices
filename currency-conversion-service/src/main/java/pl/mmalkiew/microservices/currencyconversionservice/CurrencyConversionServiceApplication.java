package pl.mmalkiew.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("pl.mmalkiew.microservices.currencyconversionservice.proxy")
public class CurrencyConversionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }

}


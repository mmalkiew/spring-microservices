package pl.mmalkiew.microservices.currencyconversionservice.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mmalkiew.microservices.currencyconversionservice.model.CurrencyConversionResponse;

@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeFeignClient {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionResponse retrieveExchangeValue(@PathVariable String from,
                                                     @PathVariable String to);
}

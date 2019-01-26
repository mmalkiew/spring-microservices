package pl.mmalkiew.microservices.currencyconversionservice.proxy;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mmalkiew.microservices.currencyconversionservice.model.CurrencyConversionResponse;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
/*
it could be used in direct invocation between microservices
 */
//@FeignClient(name = "currency-exchange-service")

/**
 * Setup ZUUL API gateway between microservices application
 */
@FeignClient(name = "api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeFeignClient {

//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionResponse retrieveExchangeValue(@PathVariable String from,
                                                     @PathVariable String to);
}

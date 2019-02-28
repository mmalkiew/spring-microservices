package pl.mmalkiew.microservices.currencyconversionservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.mmalkiew.microservices.currencyconversionservice.model.CurrencyConversionResponse;
import pl.mmalkiew.microservices.currencyconversionservice.proxy.CurrencyExchangeFeignClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);

    private final CurrencyExchangeFeignClient feignClient;

    public CurrencyConversionController(CurrencyExchangeFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse convertCurrency(@PathVariable String from,
                                                      @PathVariable String to,
                                                      @PathVariable BigDecimal quantity) {

        // Feign - Problem 1
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionResponse> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionResponse.class,
                uriVariables);

        CurrencyConversionResponse response = responseEntity.getBody();

        return new CurrencyConversionResponse(from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse convertCurrencyFeign(@PathVariable String from,
                                                           @PathVariable String to,
                                                           @PathVariable BigDecimal quantity) {

        LOGGER.info("invoke rest with param: from {} to {} quantity", from, to, quantity);

        CurrencyConversionResponse response = feignClient.retrieveExchangeValue(from, to);

        LOGGER.info("{} -> response currency exchange service", response);

        return new CurrencyConversionResponse(from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}

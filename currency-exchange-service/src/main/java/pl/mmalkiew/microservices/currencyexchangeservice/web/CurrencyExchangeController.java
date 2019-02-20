package pl.mmalkiew.microservices.currencyexchangeservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.mmalkiew.microservices.currencyexchangeservice.entity.ExchangeValue;
import pl.mmalkiew.microservices.currencyexchangeservice.model.ExchangeValueRest;
import pl.mmalkiew.microservices.currencyexchangeservice.service.ExchangeValueService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private static final String LOCAL_SERVER_PORT_PROPERTY = "local.server.port";

    private final Environment environment;
    private final ExchangeValueService service;

    public CurrencyExchangeController(Environment environment,
                                      ExchangeValueService service) {
        this.environment = environment;
        this.service = service;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValueRest retrieveExchangeValue(@PathVariable String from,
                                                   @PathVariable String to){

        LOGGER.info("invoke rest with param: from {} to {} ", from, to);

        ExchangeValueRest exchangeValueRest = new ExchangeValueRest();
        exchangeValueRest.setFrom(from);
        exchangeValueRest.setTo(to);
        exchangeValueRest.setConversionMultiple(getConversionMultiple(from, to));
        exchangeValueRest.setPort(Integer.parseInt(environment.getProperty(LOCAL_SERVER_PORT_PROPERTY)));

        LOGGER.info("{} -> model exchange value", exchangeValueRest);

        return exchangeValueRest;
    }

    private BigDecimal getConversionMultiple(String from, String to) {
        Optional<ExchangeValue> exchangeValueOptional = service.findExchangeValueByFromAndTo(from, to);
        if (exchangeValueOptional.isPresent()) {
            ExchangeValue exchangeValue = exchangeValueOptional.get();
            return exchangeValue.getConversionMultiple();
        }

        return BigDecimal.ZERO;
    }
}

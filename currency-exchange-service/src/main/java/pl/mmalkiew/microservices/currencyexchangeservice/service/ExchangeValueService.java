package pl.mmalkiew.microservices.currencyexchangeservice.service;

import pl.mmalkiew.microservices.currencyexchangeservice.entity.ExchangeValue;

import java.util.Optional;

public interface ExchangeValueService {

    Optional<ExchangeValue> findExchangeValueByFromAndTo(String from, String to);
}

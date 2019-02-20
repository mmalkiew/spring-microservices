package pl.mmalkiew.microservices.currencyexchangeservice.service.impl;

import org.springframework.stereotype.Service;
import pl.mmalkiew.microservices.currencyexchangeservice.entity.ExchangeValue;
import pl.mmalkiew.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import pl.mmalkiew.microservices.currencyexchangeservice.service.ExchangeValueService;

import java.util.Optional;

@Service
public class ExchangeValueServiceImpl implements ExchangeValueService {

    private final ExchangeValueRepository repository;

    public ExchangeValueServiceImpl(ExchangeValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ExchangeValue> findExchangeValueByFromAndTo(String from, String to) {
        return Optional.ofNullable(repository.findByFromAndTo(from, to));
    }
}

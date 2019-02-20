package pl.mmalkiew.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mmalkiew.microservices.currencyexchangeservice.entity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);
}

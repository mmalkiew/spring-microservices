package pl.mmalkiew.microservices.limitservice.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LimitsConfigurationController.class);

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        LimitConfiguration limitConfiguration = new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
        return limitConfiguration;
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackDefaultConfiguration")
    public LimitConfiguration getConfiguration() {
        LOGGER.error("getting configuration error. Configuration not available ...");
        throw new RuntimeException("Not available");
    }

    public LimitConfiguration fallbackDefaultConfiguration() {
        LOGGER.info("get default configuration ...");
        return new LimitConfiguration(999, 9);
    }


}
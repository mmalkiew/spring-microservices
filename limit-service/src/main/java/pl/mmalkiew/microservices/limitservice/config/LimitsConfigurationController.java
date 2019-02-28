package pl.mmalkiew.microservices.limitservice.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

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

    @GetMapping("/test")
    public @ResponseBody
    CompletableFuture<String> test()  {
        return findByEmail().thenApplyAsync(user -> {
            return user;
        });
    }


    @Async
    public CompletableFuture<String> findByEmail() {
        String test = "test" + LocalDateTime.now().toString();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(test);
    }


}
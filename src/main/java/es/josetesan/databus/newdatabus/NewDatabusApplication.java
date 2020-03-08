package es.josetesan.databus.newdatabus;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewDatabusApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NewDatabusApplication.class, args);
    }


    @Autowired
    private MyBean myBean;

    @Override
    public void run(String... args) throws Exception {

        RetryConfig config = RetryConfig.custom()
                     .maxAttempts(3)
                     .retryExceptions(MyException.class, InterruptedException.class)
                     .build();

        RetryRegistry registry = RetryRegistry.of(config);


        Retry retryWithDefaultConfig = registry.retry("name1");

        // Decorate the invocation of the HelloWorldService
        Runnable runnable = Retry.decorateRunnable(retryWithDefaultConfig, myBean);

        Try.runRunnable(runnable);

    }
}

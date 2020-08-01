package es.josetesan.databus.newdatabus;


import io.github.resilience4j.retry.annotation.Retry;
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

    public void run(String... args)  {
          myBean.run();
    }
}

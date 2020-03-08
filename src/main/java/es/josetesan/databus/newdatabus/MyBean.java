package es.josetesan.databus.newdatabus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyBean implements  Runnable{

    private final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public static final Logger LOGGER = LoggerFactory.getLogger(MyBean.class);

    @Override
    public void run()  {

        while (atomicBoolean.get()) {
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted");
            }
            LOGGER.info("Running bean with counter equals to {}", atomicInteger.get());
            int value = atomicInteger.incrementAndGet();
            if (value % 10 == 0) {
                throw new MyException("El contador es multiplo de 10");
            }

        }


    }
}

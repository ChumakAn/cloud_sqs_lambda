package com.contactsunny.poc.AmazonSQSProducerPOC;

import com.contactsunny.poc.AmazonSQSProducerPOC.utils.SensorDataGenerator;
import com.contactsunny.poc.AmazonSQSProducerPOC.utils.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private SensorDataGenerator sensorDataGenerator;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            Type[] types = Type.values();
            for (Type type : types) {
                sensorDataGenerator.generateSensorDataByType(type);
                TimeUnit.SECONDS.sleep(30);
            }
        }
    }
}

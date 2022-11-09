package com.contactsunny.poc.AmazonSQSConsumerPOC;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        FunctionalSpringApplication.run(App.class, args);
    }


}

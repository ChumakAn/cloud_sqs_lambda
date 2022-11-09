package com.contactsunny.poc.AmazonSQSProducerPOC.utils;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SensorDataGenerator {
    private final Random random = new Random();
    private static final String SEPARATOR = ",";

    public String generateSensorDataByType(Type type) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id:").append(generateId())
                .append(SEPARATOR).append("type:").append(type.toString())
                .append(SEPARATOR).append("x:").append(generateCoordinate())
                .append(SEPARATOR).append("y:").append(generateCoordinate())
                .append(SEPARATOR).append("timestamp:").append(LocalDateTime.now())
                .append(SEPARATOR).append("value:").append(generateValue(type));
        return stringBuilder.toString();
    }

    private Double generateCoordinate() {
        return random.nextDouble(0.0, 100000.0);
    }

    private Double generateValue(Type type) {
        return type == Type.TEMPERATURE
                ? random.nextDouble(-200.0, 100000.0)
                : random.nextDouble(0.0, 100000.0);
    }

    private Long generateId() {
        return random.nextLong(100);
    }

}

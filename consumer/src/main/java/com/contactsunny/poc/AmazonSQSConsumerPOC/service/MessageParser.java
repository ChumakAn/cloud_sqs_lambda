package com.contactsunny.poc.AmazonSQSConsumerPOC.service;

import com.contactsunny.poc.AmazonSQSConsumerPOC.enums.Type;
import com.contactsunny.poc.AmazonSQSConsumerPOC.model.Sensor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageParser {
    private static final String SEPARATOR = ",";
    private static final String SEMICOLUMN = ":";

    public Sensor parseDataToSensor(String message) {
        String[] splitted =  message.split(SEPARATOR);
        Sensor.SensorBuilder sensor = Sensor.builder();
        for (String split : splitted) {
           String[] strings = split.split(SEMICOLUMN);
           switch (strings[0]) {
               case "type" :
                   sensor.type(Type.valueOf(strings[1].toUpperCase()));
                   break;
               case "x" :
                   sensor.xCoordinate(Double.valueOf(strings[1]));
                   break;
               case "y" :
                   sensor.yCoordinate(Double.valueOf(strings[1]));
                   break;
               case "timestamp":
                   sensor.dataTime(LocalDateTime.parse(strings[1]));
                   break;
               case "value" :
                   sensor.value(Double.valueOf(strings[1]));
                   break;
           }
        }
        return sensor.build();
    }
}

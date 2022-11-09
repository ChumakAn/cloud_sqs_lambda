package com.contactsunny.poc.AmazonSQSProducerPOC.service;

import com.contactsunny.poc.AmazonSQSProducerPOC.utils.SQSUtil;
import com.contactsunny.poc.AmazonSQSProducerPOC.utils.SensorDataGenerator;
import com.contactsunny.poc.AmazonSQSProducerPOC.utils.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private final SQSUtil sqsUtil;
    private final SensorDataGenerator sensorDataGenerator;
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);


    public DataService(SQSUtil sqsUtil, SensorDataGenerator sensorDataGenerator) {
        this.sqsUtil = sqsUtil;
        this.sensorDataGenerator = sensorDataGenerator;
    }

    public void generateRandomDataAndSendData(Type type) {
       String payload = sensorDataGenerator.generateSensorDataByType(type);
       logger.info("payload {}", payload);
       sqsUtil.sendSQSMessage(payload);
       logger.info("Message was sent !");
    }
}

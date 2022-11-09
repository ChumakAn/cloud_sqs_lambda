package com.contactsunny.poc.AmazonSQSConsumerPOC;

import com.contactsunny.poc.AmazonSQSConsumerPOC.utils.SQSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SensorFunction implements Function<Void, Void> {

    @Autowired
    SQSUtil sqsUtil;


    @Override
    public Void apply(Void unused) {
        sqsUtil.startListeningToMessages();
        return null;
    }
}

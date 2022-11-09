package com.contactsunny.poc.AmazonSQSConsumerPOC.utils;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.contactsunny.poc.AmazonSQSConsumerPOC.model.Sensor;
import com.contactsunny.poc.AmazonSQSConsumerPOC.service.MessageParser;
import com.contactsunny.poc.AmazonSQSConsumerPOC.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SQSUtil {

    @Value("${sqs.url}")
    private String sqsUrl;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    private AmazonSQS amazonSQS;

    private final MessageParser messageParser;
    private final SensorService service;

    private static final Logger logger = LoggerFactory.getLogger(SQSUtil.class);

    @PostConstruct
    private void postConstructor() {

        logger.info("SQS URL: " + sqsUrl);

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        this.amazonSQS = AmazonSQSClientBuilder.standard().withCredentials(awsCredentialsProvider).withRegion(awsRegion).build();
    }

    @SqsListener("https://sqs.us-east-1.amazonaws.com/580253415876/iot-sqs")
    public void startListeningToMessages() {
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl)
                .withMaxNumberOfMessages(1)
                .withWaitTimeSeconds(3);
        final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        for (Message messageObject : messages) {
            String message = messageObject.getBody();
            Sensor sensor = messageParser.parseDataToSensor(message);
            logger.info("Received message: " + message);
            service.create(sensor);
            deleteMessage(messageObject);
        }
    }

    private void deleteMessage(Message messageObject) {

        final String messageReceiptHandle = messageObject.getReceiptHandle();
        amazonSQS.deleteMessage(new DeleteMessageRequest(sqsUrl, messageReceiptHandle));

    }
}

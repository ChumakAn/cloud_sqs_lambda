package com.contactsunny.poc.AmazonSQSConsumerPOC.repository;

import com.contactsunny.poc.AmazonSQSConsumerPOC.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}

package com.contactsunny.poc.AmazonSQSConsumerPOC.service;


import com.contactsunny.poc.AmazonSQSConsumerPOC.model.Sensor;
import com.contactsunny.poc.AmazonSQSConsumerPOC.repository.SensorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> getAll() {
        return sensorRepository.findAll();
    }

    public Sensor getById(Long id) {
        return sensorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Sensor create(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Sensor update(Long id, Sensor sensor) {
        if (sensorRepository.findById(id).isPresent()) {
            return sensorRepository.save(sensor);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        if (sensorRepository.findById(id).isPresent()) {
            sensorRepository.deleteById(id);
        }
    }

}

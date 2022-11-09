package com.contactsunny.poc.AmazonSQSConsumerPOC.model;

import com.contactsunny.poc.AmazonSQSConsumerPOC.enums.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private Double xCoordinate;

    @Column
    private Double yCoordinate;

    @CreationTimestamp
    private LocalDateTime dataTime;

    @Column
    private Double value;
}

package com.simononboard.iotserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private SensorId sensorId;
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    @Enumerated(EnumType.STRING)
    private TypeValue typeValue;
    private String value;
}

package com.simononboard.iotserver.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RawInfo {
    private SensorId sensorID;
    private SensorType sensorType;
    private MessageType messageType;
    private TypeValue typeVariable;
    private String value;
}

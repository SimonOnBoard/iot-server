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
    private SensorId sensorId;
    private SensorType sensorType;
    private MessageType messageType;
    private TypeValue typeValue;
    private String value;

    public Measurement convertToMeasurement() {
        return Measurement.builder()
                .messageType(messageType)
                .sensorId(sensorId)
                .sensorType(sensorType)
                .typeValue(typeValue)
                .value(value)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}

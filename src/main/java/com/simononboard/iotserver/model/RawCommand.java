package com.simononboard.iotserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RawCommand {
    private SensorId sensorId;
    private SensorType sensorType;
    private MessageType messageType;
    private TypeValue typeValue;
    private String value;
}

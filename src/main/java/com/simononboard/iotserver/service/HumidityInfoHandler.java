package com.simononboard.iotserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simononboard.iotserver.model.Measurement;
import com.simononboard.iotserver.model.SensorType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class HumidityInfoHandler {

    private final LedManipulator ledManipulator;

    public void handle(Measurement measurement) throws JsonProcessingException {
        if (!measurement.getSensorType().equals(SensorType.HUMIDITY)) {
            return;
        }
        log.info("Humidity is " + measurement.getValue());
        double value = Double.parseDouble(measurement.getValue());
        if (value < 30 || value > 60) {
            ledManipulator.setColor(255, 0, 0);
        } else if (value < 40 || value > 50) {
            ledManipulator.setColor(255, 255, 0);
        } else {
            ledManipulator.setColor(0, 255, 0);
        }
    }
}

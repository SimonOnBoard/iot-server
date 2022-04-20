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
public class TemperatureInfoHandler {

    private final AngleManipulator angleManipulator;

    public void handle(Measurement measurement) throws JsonProcessingException {
        if (!measurement.getSensorType().equals(SensorType.TEMPERATURE)) {
            return;
        }
        log.info("Temperature is " + measurement.getValue());
        double value = Double.parseDouble(measurement.getValue());
        if (value > 30) {
            angleManipulator.setAngle(6);
        } else if (value > 28) {
            angleManipulator.setAngle(5);
        } else if (value > 26) {
            angleManipulator.setAngle(4);
        } else if (value > 24) {
            angleManipulator.setAngle(3);
        } else if (value > 23) {
            angleManipulator.setAngle(2);
        } else if (value > 22) {
            angleManipulator.setAngle(1);
        } else {
            angleManipulator.setAngle(0);
        }
    }
}

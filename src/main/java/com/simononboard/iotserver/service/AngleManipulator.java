package com.simononboard.iotserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simononboard.iotserver.model.*;
import com.simononboard.iotserver.service.KafkaSenderExample;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AngleManipulator {

    private final ObjectMapper objectMapper;

    private final KafkaSenderExample kafkaSenderExample;

    void setAngle(int value) throws JsonProcessingException {
        if (value < 0 || value > 6) {
            log.error("Value of ANGLE must be: 0,1,2,3,4,5,6");
            return;
        }
        RawCommand rawCommand = RawCommand.builder()
                .sensorId(SensorId.SECOND)
                .messageType(MessageType.COMMAND)
                .sensorType(SensorType.MOTION)
                .typeValue(TypeValue.ANGLE)
                .value(String.valueOf(value * 30))
                .build();
        final var result = objectMapper.writeValueAsString(rawCommand);
        log.info(result);
        kafkaSenderExample.sendMessage(result, "device-command");
    }
}

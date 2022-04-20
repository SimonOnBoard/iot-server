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
public class LedManipulator {
    private final ObjectMapper objectMapper;

    private final KafkaSenderExample kafkaSenderExample;

    public void setColor(int r, int g, int b) throws JsonProcessingException {
        String value = r + "-" + g + "-" + b;
        RawCommand rawCommand = RawCommand.builder()
                .sensorId(SensorId.THIRD)
                .messageType(MessageType.COMMAND)
                .sensorType(SensorType.LED)
                .typeValue(TypeValue.COLOR)
                .value(value)
                .build();
        final var result = objectMapper.writeValueAsString(rawCommand);
        log.info(result);
        kafkaSenderExample.sendMessage(result, "device-command");
    }
}

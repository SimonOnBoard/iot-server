package com.simononboard.iotserver.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simononboard.iotserver.model.MessageType;
import com.simononboard.iotserver.model.RawCommand;
import com.simononboard.iotserver.model.SensorId;
import com.simononboard.iotserver.model.SensorType;
import com.simononboard.iotserver.model.TypeValue;
import com.simononboard.iotserver.service.KafkaSenderExample;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeviceManipulationController {
    private final KafkaSenderExample kafkaSenderExample;
    private final ObjectMapper objectMapper;
    private final Map<SensorId, SensorType> sensorTypeMap;
    private final Map<SensorType, TypeValue> sensorTypeValueMap;

    @PostMapping("/sensor/{sensor_id}/value/{sensor_value}")
    public ResponseEntity<Void> sendCommandToDevice(@PathVariable("sensor_id") SensorId sensorId,
                                                    @PathVariable("sensor_value") String sensorValue) throws JsonProcessingException {
        final SensorType sensorType = sensorTypeMap.get(sensorId);
        RawCommand rawCommand = RawCommand.builder()
                .sensorId(sensorId)
                .messageType(MessageType.COMMAND)
                .sensorType(sensorType)
                .typeValue(sensorTypeValueMap.get(sensorType))
                .value(sensorValue)
                .build();
        final var result = objectMapper.writeValueAsString(rawCommand);
        log.info(result);
        kafkaSenderExample.sendMessage(result, "device-command");
        return ResponseEntity.ok().build();
    }
}

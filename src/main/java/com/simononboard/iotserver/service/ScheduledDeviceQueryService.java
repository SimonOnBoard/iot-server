package com.simononboard.iotserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simononboard.iotserver.model.RawInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledDeviceQueryService {
    private final DeviceQueryService deviceQueryService;

    static String URL = "https://iot-gateway-team3.herokuapp.com/sensors";

    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5_000)
    void query() throws JsonProcessingException {
        ArrayList<RawInfo> result = deviceQueryService.queryForRawInfo(URL);
        log.info(objectMapper.writeValueAsString(result));
    }
}

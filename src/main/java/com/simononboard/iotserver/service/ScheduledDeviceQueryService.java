package com.simononboard.iotserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simononboard.iotserver.model.Measurement;
import com.simononboard.iotserver.model.RawInfo;
import com.simononboard.iotserver.model.SensorType;
import com.simononboard.iotserver.repo.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledDeviceQueryService {
    private final DeviceQueryService deviceQueryService;

    private final MeasurementRepository measurementRepository;

    private final HumidityInfoHandler humidityInfoHandler;

    private final TemperatureInfoHandler temperatureInfoHandler;

    static String URL = "https://iot-gateway-team3.herokuapp.com/sensors";

    @Scheduled(fixedDelay = 5_000)
    void query() {
        ArrayList<RawInfo> result = deviceQueryService.queryForRawInfo(URL);
        result.forEach(r -> handleMeasurement(r.convertToMeasurement()));
    }

    @SneakyThrows
    void handleMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
        if (measurement.getSensorType().equals(SensorType.HUMIDITY)) {
            humidityInfoHandler.handle(measurement);
        } else if (measurement.getSensorType().equals(SensorType.TEMPERATURE)) {
            temperatureInfoHandler.handle(measurement);
        }
    }
}

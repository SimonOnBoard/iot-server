package com.simononboard.iotserver.configuration;

import com.simononboard.iotserver.model.SensorId;
import com.simononboard.iotserver.model.SensorType;
import com.simononboard.iotserver.model.TypeValue;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaTopicConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("device-command").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("device-info").build();
    }

    @Bean
    public Map<SensorId, SensorType> sensorTypeMap() {
        var result = new HashMap<SensorId, SensorType>();
        result.put(SensorId.FIRST, SensorType.HUMIDITY);
        result.put(SensorId.SECOND, SensorType.MOTION);
        result.put(SensorId.THIRD, SensorType.LED);
        result.put(SensorId.FOURTH, SensorType.TEMPERATURE);
        return result;
    }

    @Bean
    public Map<SensorType, TypeValue> sensorTypeValueMap() {
        var result = new HashMap<SensorType, TypeValue>();
        result.put(SensorType.HUMIDITY, TypeValue.PERCENT);
        result.put(SensorType.TEMPERATURE, TypeValue.DEGREES);
        result.put(SensorType.MOTION, TypeValue.ANGLE);
        result.put(SensorType.LED, TypeValue.COLOR);
        return result;
    }
}
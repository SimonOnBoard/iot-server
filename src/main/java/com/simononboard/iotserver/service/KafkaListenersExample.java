package com.simononboard.iotserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class KafkaListenersExample {

//
//    @KafkaListener(topics = "device-command", groupId = "myGroup")
//    void listener(String data) {
//        log.info(data);
//    }


    /**
     * Здесь должен вычитываться из очереди и
     * в дальнейшем обрабатываться для визуализации
     * {@link com.simononboard.iotserver.model.RawInfo}
     *
     * @param data
     */
    @KafkaListener(topics = "device-info", groupId = "myGroup")
    void listenerInfoFromSensor(String data) {
        log.info(data);
    }

}
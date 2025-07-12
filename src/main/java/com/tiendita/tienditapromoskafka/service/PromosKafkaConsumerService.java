package com.tiendita.tienditapromoskafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@Slf4j
public class PromosKafkaConsumerService {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "${kafka.topic.ventas}", groupId = "${spring.kafka.consumer.group-id}")
    public void procesarVenta(@Payload String message,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.OFFSET) long offset,
                             Acknowledgment acknowledgment) {
        try {
            System.out.println("MENSAJE DE VENTA RECIBIDO EN TOPIC VENTAS: " + message);          
            acknowledgment.acknowledge();
        } catch (Exception e) {
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = "${kafka.topic.stock}", groupId = "${spring.kafka.consumer.group-id}")
    public void procesarStock(@Payload String message,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.OFFSET) long offset,
                             Acknowledgment acknowledgment) {
        try {
            System.out.println("MENSAJE DE ACTUALIZACION DE STOCK RECIBIDO EN TOPIC STOCK: " + message);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            acknowledgment.acknowledge();
        }
    }
}

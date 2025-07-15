package com.tiendita.tienditapromoskafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.stock}")
    private String stockTopic;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void enviarMensajeStock(List<?> ventasData) {
        try {
            String mensaje = objectMapper.writeValueAsString(ventasData);
            log.info("Enviando mensaje al topic stock: {}", mensaje);
            
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(stockTopic, mensaje);
            
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Mensaje enviado exitosamente al topic stock: {} con offset: {}", 
                             mensaje, result.getRecordMetadata().offset());
                } else {
                    log.error("Error al enviar mensaje al topic stock: {}", mensaje, ex);
                }
            });
            
        } catch (JsonProcessingException e) {
            log.error("Error al serializar el mensaje para el topic stock", e);
            throw new RuntimeException("Error al procesar el mensaje para Kafka", e);
        }
    }
}

package com.tiendita.tienditapromoskafka.controller;

import com.tiendita.tienditapromoskafka.dto.VentaDto;
import com.tiendita.tienditapromoskafka.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/actualizar")
    public ResponseEntity<String> actualizarStock(@RequestBody List<VentaDto> ventas) {
        try {
            log.info("Recibida solicitud de actualización de stock con {} ventas", ventas.size());
            
            // Reenviar el contenido de las ventas a la cola stock en Kafka
            kafkaProducerService.enviarMensajeStock(ventas);
            
            log.info("Ventas reenviadas exitosamente al topic stock de Kafka");
            
            return ResponseEntity.ok("Ventas procesadas y enviadas al topic stock exitosamente");
            
        } catch (Exception e) {
            log.error("Error al procesar las ventas y enviar al topic stock", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar las ventas: " + e.getMessage());
        }
    }

}

package com.tiendita.tienditapromoskafka.controller;

import com.tiendita.tienditapromoskafka.model.Promocion;
import com.tiendita.tienditapromoskafka.service.PromocionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promociones")
@Slf4j
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @PostMapping("/crear")
    public ResponseEntity<Promocion> crearPromocion() {
        try {
            Promocion promocion = promocionService.crearPromocionProductoMayorStock();
            return ResponseEntity.ok(promocion);
        } catch (Exception e) {
            log.error("Error al crear promoción: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<Promocion>> obtenerPromocionesVigentes() {
        try {
            List<Promocion> promociones = promocionService.obtenerPromocionesVigentes();
            return ResponseEntity.ok(promociones);
        } catch (Exception e) {
            log.error("Error al obtener promociones vigentes: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

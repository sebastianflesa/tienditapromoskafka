package com.tiendita.tienditapromoskafka.service;

import com.tiendita.tienditapromoskafka.model.Promocion;
import com.tiendita.tienditapromoskafka.model.Producto;
import com.tiendita.tienditapromoskafka.repository.PromocionRepository;
import com.tiendita.tienditapromoskafka.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Promocion crearPromocionProductoMayorStock() {
        try {

            PageRequest pageRequest = PageRequest.of(0, 1);
            Optional<Producto> productoOpt = productoRepository.findProductoConMayorStock(pageRequest).stream().findFirst();
            Producto producto = productoOpt.get();
            Promocion promocion = new Promocion();
            double descuentoAleatorio = ThreadLocalRandom.current().nextDouble(0.10, 0.91);
            promocion.setProductoId(producto.getId());
            promocion.setDescripcion("PROMO DE " + Math.round(descuentoAleatorio*100) + "% PARA " + producto.getNombre() + " CON MAYOR STOCK" + " (" + producto.getStock() + ")");
            promocion.setDescuento((double) Math.round(descuentoAleatorio*100));
            promocion.setFechaVencimiento(LocalDate.now().plusDays(30));
            promocion.setValido(1);
            promocionRepository.save(promocion);
            return promocion;
            
        } catch (Exception e) {
            log.error("Error al crear promo para producto con mayor stock: {}", e.getMessage(), e);
            throw new RuntimeException("Error al crear promo: " + e.getMessage());
        }
    }

    public List<Promocion> obtenerPromocionesVigentes() {
        return promocionRepository.findPromocionesVigentes();
    }

}

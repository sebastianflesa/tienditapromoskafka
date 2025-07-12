package com.tiendita.tienditapromoskafka.repository;

import com.tiendita.tienditapromoskafka.model.Producto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    @Query("SELECT p FROM Producto p ORDER BY p.stock DESC")
    Optional<Producto> findProductoConMayorStock(PageRequest pageRequest);
}

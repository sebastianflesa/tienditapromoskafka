package com.tiendita.tienditapromoskafka.repository;

import com.tiendita.tienditapromoskafka.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {
    
    @Query("SELECT p FROM Promocion p WHERE p.valido = 1 ORDER BY ID DESC")
    List<Promocion> findPromocionesVigentes();
}

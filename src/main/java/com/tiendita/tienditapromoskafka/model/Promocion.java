package com.tiendita.tienditapromoskafka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "PROMOCIONES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promocion_seq_gen")
    @SequenceGenerator(name = "promocion_seq_gen", sequenceName = "PROMOCIONES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PRODUCTO_ID", nullable = false)
    private Long productoId;
    
    @Column(name = "DESCRIPCION", length = 120)
    private String descripcion;
    
    @Column(name = "DESCUENTO")
    private Double descuento;
    
    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechaVencimiento;
    
    @Column(name = "VALIDO")
    private Integer valido = 1;
}

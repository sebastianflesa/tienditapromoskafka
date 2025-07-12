package com.tiendita.tienditapromoskafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoInventarioDto {
    private Long id;
    private String nombre;
    private Long stock;
    private Long valor;
}

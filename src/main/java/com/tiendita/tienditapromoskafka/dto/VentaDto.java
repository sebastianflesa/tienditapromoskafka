package com.tiendita.tienditapromoskafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private Long productoId;
    private Integer cantidad;
    private Long clienteId;
}

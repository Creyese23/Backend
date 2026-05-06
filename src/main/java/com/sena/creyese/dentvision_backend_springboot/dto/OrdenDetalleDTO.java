package com.sena.creyese.dentvision_backend_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDetalleDTO {
    private Long idOrdenDetalle;
    private Long idOrden;
    private Long idServicio;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private String observaciones;
}

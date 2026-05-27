package com.sena.creyese.dentvision_backend_springboot.dto;

import com.sena.creyese.dentvision_backend_springboot.enums.EstadoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long idPago;
    private Long idFactura;
    private LocalDate fechaPago;
    private String metodoPago;
    private BigDecimal valor;
    private EstadoPago estado;
}

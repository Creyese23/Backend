package com.sena.creyese.dentvision_backend_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaServicioDTO {
    private Long idCitaServicio;
    private Long idCita;
    private Long idServicio;
    private BigDecimal precioAcordado;
}

package com.sena.creyese.dentvision_backend_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cita_servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCitaServicio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;
    
    @Column(name = "precio_acordado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioAcordado;
}

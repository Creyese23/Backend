package com.sena.creyese.dentvision_backend_springboot.entity;

import com.sena.creyese.dentvision_backend_springboot.enums.EntregaEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "entrega")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;
    
    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EntregaEstado estado;
    
    @Column(name = "observaciones", length = 500)
    private String observaciones;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrden", nullable = false)
    private Orden orden;
}

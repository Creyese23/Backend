package com.sena.creyese.dentvision_backend_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "mensaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;
    
    @Column(name = "contenido", nullable = false, length = 1000)
    private String contenido;
    
    @Column(name = "remitente", nullable = false, length = 50)
    private String remitente;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConversacion", nullable = false)
    private Conversacion conversacion;
}

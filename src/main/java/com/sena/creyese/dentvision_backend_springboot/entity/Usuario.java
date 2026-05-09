package com.sena.creyese.dentvision_backend_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @Column(name = "usuario_por_defecto", nullable = false)
    private Boolean usuarioPorDefecto = false;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "ACTIVO";

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
    
    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Empleado empleado;
    
    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Paciente paciente;
}

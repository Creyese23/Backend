package com.sena.creyese.dentvision_backend_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;
    
    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;
    
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "ACTIVO";
    
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpleadoRol> empleadoRoles = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario", unique = true)
    @JsonIgnore
    private Usuario usuario;
}

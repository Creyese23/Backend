package com.sena.creyese.dentvision_backend_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleadoRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

}


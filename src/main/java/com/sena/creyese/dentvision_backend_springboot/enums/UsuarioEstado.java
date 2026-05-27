package com.sena.creyese.dentvision_backend_springboot.enums;

import lombok.Getter;

@Getter
public enum UsuarioEstado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    private final String descripcion;

    UsuarioEstado(String descripcion) {
        this.descripcion = descripcion;
    }

}

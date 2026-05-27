package com.sena.creyese.dentvision_backend_springboot.enums;

import lombok.Getter;

@Getter
public enum ServicioEstado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    DISCONTINUADO("Discontinuado");

    private final String descripcion;

    ServicioEstado(String descripcion) {
        this.descripcion = descripcion;
    }

}

package com.sena.creyese.dentvision_backend_springboot.enums;

public enum ServicioEstado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    DISCONTINUADO("Discontinuado");

    private final String descripcion;

    ServicioEstado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

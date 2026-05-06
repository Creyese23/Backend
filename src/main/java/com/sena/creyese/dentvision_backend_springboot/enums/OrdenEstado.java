package com.sena.creyese.dentvision_backend_springboot.enums;

public enum OrdenEstado {
    PENDIENTE("Pendiente"),
    EN_PROCESO("En Proceso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada");
    
    private final String descripcion;
    
    OrdenEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

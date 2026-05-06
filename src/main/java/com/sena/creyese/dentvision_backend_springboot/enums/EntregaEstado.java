package com.sena.creyese.dentvision_backend_springboot.enums;

public enum EntregaEstado {
    PENDIENTE("Pendiente"),
    ENTREGADA("Entregada"),
    RECHAZADA("Rechazada");
    
    private final String descripcion;
    
    EntregaEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

package com.sena.creyese.dentvision_backend_springboot.enums;

public enum PagoEstado {
    PENDIENTE("Pendiente"),
    PROCESADO("Procesado"),
    FALLIDO("Fallido"),
    CANCELADO("Cancelado");
    
    private final String descripcion;
    
    PagoEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

package com.sena.creyese.dentvision_backend_springboot.enums;

public enum EstadoPago {
    PENDIENTE("Pendiente"),
    PARCIAL("Parcial"),
    PAGADA("Pagada"),
    VENCIDA("Vencida");
    
    private final String descripcion;
    
    EstadoPago(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

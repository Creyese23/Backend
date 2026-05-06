package com.sena.creyese.dentvision_backend_springboot.enums;

public enum CitaEstado {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    REALIZADA("Realizada"),
    CANCELADA("Cancelada");
    
    private final String descripcion;
    
    CitaEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

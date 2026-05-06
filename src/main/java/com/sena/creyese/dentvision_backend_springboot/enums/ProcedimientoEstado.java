package com.sena.creyese.dentvision_backend_springboot.enums;

public enum ProcedimientoEstado {
    PENDIENTE("Pendiente"),
    EN_PROCESO("En Proceso"),
    COMPLETADO("Completado"),
    CANCELADO("Cancelado");
    
    private final String descripcion;
    
    ProcedimientoEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

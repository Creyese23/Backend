package com.sena.creyese.dentvision_backend_springboot.enums;

public enum TipoMovimientoInventario {
    ENTRADA("Entrada"),
    SALIDA("Salida"),
    AJUSTE("Ajuste");
    
    private final String descripcion;
    
    TipoMovimientoInventario(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

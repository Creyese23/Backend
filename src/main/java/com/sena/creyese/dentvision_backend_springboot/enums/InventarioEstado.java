package com.sena.creyese.dentvision_backend_springboot.enums;

public enum InventarioEstado {
    DISPONIBLE("Disponible"),
    AGOTADO("Agotado"),
    DAÑADO("Dañado"),
    DESCONTINUADO("Descontinuado");
    
    private final String descripcion;
    
    InventarioEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

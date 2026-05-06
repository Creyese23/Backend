package com.sena.creyese.dentvision_backend_springboot.enums;

public enum EmpleadoEstado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    SUSPENDIDO("Suspendido");
    
    private final String descripcion;
    
    EmpleadoEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

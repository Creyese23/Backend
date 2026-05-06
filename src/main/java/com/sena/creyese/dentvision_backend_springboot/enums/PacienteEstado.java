package com.sena.creyese.dentvision_backend_springboot.enums;

public enum PacienteEstado {
    ACTIVO("Activo"),
    INACTIVO("Inactivo");
    
    private final String descripcion;
    
    PacienteEstado(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

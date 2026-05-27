package com.sena.creyese.dentvision_backend_springboot.dto;

import com.sena.creyese.dentvision_backend_springboot.enums.EmpleadoEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    private Long idEmpleado;
    private String nombres;
    private String apellidos;
    private String documento;
    private String telefono;
    private EmpleadoEstado estado;
    private UsuarioDTO usuario;
}

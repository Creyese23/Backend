package com.sena.creyese.dentvision_backend_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRolDTO {
    private Long idEmpleadoRol;
    private Long idEmpleado;
    private Long idRol;
}

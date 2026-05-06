package com.sena.creyese.dentvision_backend_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private Long idEmpleado;
    private String nombres;
    private String apellidos;
    private String rol;
}

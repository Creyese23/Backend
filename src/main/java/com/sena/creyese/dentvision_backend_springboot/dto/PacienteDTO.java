package com.sena.creyese.dentvision_backend_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private Long idPaciente;
    private String nombres;
    private String apellidos;
    private String documento;
    private String telefono;
    private String correo;
    private String direccion;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    
    private String estado;
    private UsuarioDTO usuario;
}

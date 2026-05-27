package com.sena.creyese.dentvision_backend_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sena.creyese.dentvision_backend_springboot.enums.PacienteEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private Long idPaciente;
    private String nombres;
    private String apellidos;
    private String documento;
    private String telefono;
    private String direccion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private PacienteEstado estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaEliminacion;
    private UsuarioDTO usuario;
}

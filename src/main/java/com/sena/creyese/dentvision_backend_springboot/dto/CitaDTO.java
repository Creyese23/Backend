package com.sena.creyese.dentvision_backend_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long idCita;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;
    
    private String estado;
    private String motivo;
    private Long idPaciente;
    private Long idOdontologo;
}

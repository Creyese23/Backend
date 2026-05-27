package com.sena.creyese.dentvision_backend_springboot.dto;

import com.sena.creyese.dentvision_backend_springboot.enums.UsuarioEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String email;
    private String password;
    private Boolean usuarioPorDefecto;
    private UsuarioEstado estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaEliminacion;
}

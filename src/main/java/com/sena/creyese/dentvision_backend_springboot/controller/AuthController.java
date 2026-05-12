package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.LoginRequest;
import com.sena.creyese.dentvision_backend_springboot.dto.LoginResponse;
import com.sena.creyese.dentvision_backend_springboot.entity.Empleado;
import com.sena.creyese.dentvision_backend_springboot.entity.EmpleadoRol;
import com.sena.creyese.dentvision_backend_springboot.entity.Usuario;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRolRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.PacienteRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.UsuarioRepository;
import com.sena.creyese.dentvision_backend_springboot.security.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UsuarioRepository usuarioRepository;

    @Setter
    @Getter
    private EmpleadoRepository empleadoRepository;

    @Setter
    @Getter
    private PacienteRepository pacienteRepository;

    private final EmpleadoRolRepository empleadoRolRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioRepository usuarioRepository, EmpleadoRepository empleadoRepository, PacienteRepository pacienteRepository, EmpleadoRolRepository empleadoRolRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.empleadoRepository = empleadoRepository;
        this.pacienteRepository = pacienteRepository;
        this.empleadoRolRepository = empleadoRolRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String token = jwtUtil.generateToken(loginRequest.getEmail());

            // Buscar usuario por email
            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).orElse(null);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            String tipoUsuario;
            Long idPersona;
            String nombres;
            String apellidos;
            String rolNombre = null;

            // Verificar si es empleado
            if (usuario.getEmpleado() != null) {
                Empleado empleado = usuario.getEmpleado();
                tipoUsuario = "EMPLEADO";
                idPersona = empleado.getIdEmpleado();
                nombres = empleado.getNombres();
                apellidos = empleado.getApellidos();
                // Obtener el primer rol del empleado
                List<EmpleadoRol> empleadoRoles = empleadoRolRepository.findByEmpleado_IdEmpleado(empleado.getIdEmpleado());
                if (!empleadoRoles.isEmpty()) {
                    rolNombre = empleadoRoles.getFirst().getRol().getNombreRol();
                }
            } else if (usuario.getPaciente() != null) {
                // Si es paciente
                var paciente = usuario.getPaciente();
                tipoUsuario = "PACIENTE";
                idPersona = paciente.getIdPaciente();
                nombres = paciente.getNombres();
                apellidos = paciente.getApellidos();
                rolNombre = "PACIENTE";
            } else {
                // Usuario sin tipo válido
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            LoginResponse response = new LoginResponse(
                    token, // token
                    "Bearer", // tipo
                    usuario.getIdUsuario(), // idUsuario
                    usuario.getEmail(), // email
                    tipoUsuario, // tipoUsuario
                    idPersona, // idPersona
                    nombres,
                    apellidos,
                    rolNombre
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}


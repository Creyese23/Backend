package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.LoginRequest;
import com.sena.creyese.dentvision_backend_springboot.dto.LoginResponse;
import com.sena.creyese.dentvision_backend_springboot.entity.Empleado;
import com.sena.creyese.dentvision_backend_springboot.entity.EmpleadoRol;
import com.sena.creyese.dentvision_backend_springboot.entity.Paciente;
import com.sena.creyese.dentvision_backend_springboot.entity.Usuario;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRolRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.PacienteRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.UsuarioRepository;
import com.sena.creyese.dentvision_backend_springboot.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EmpleadoRolRepository empleadoRolRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String token = jwtUtil.generateToken(loginRequest.getEmail());

            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                    .orElse(null);

            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            String tipoUsuario = null;
            Long idPersona = null;
            String nombres = null;
            String apellidos = null;
            String rol = null;

            // Check if it's an Empleado
            if (usuario.getEmpleado() != null) {
                Empleado empleado = usuario.getEmpleado();
                tipoUsuario = "EMPLEADO";
                idPersona = empleado.getIdEmpleado();
                nombres = empleado.getNombres();
                apellidos = empleado.getApellidos();

                // Obtener el primer rol del empleado
                List<EmpleadoRol> empleadoRoles = empleadoRolRepository.findByEmpleado_IdEmpleado(empleado.getIdEmpleado());
                if (!empleadoRoles.isEmpty()) {
                    rol = empleadoRoles.get(0).getRol().getNombreRol();
                }
            }
            // Check if it's a Paciente
            else if (usuario.getPaciente() != null) {
                Paciente paciente = usuario.getPaciente();
                tipoUsuario = "PACIENTE";
                idPersona = paciente.getIdPaciente();
                nombres = paciente.getNombres();
                apellidos = paciente.getApellidos();
                rol = "PACIENTE";
            }

            LoginResponse response = new LoginResponse(
                    token,
                    "Bearer",
                    usuario.getIdUsuario(),
                    usuario.getEmail(),
                    tipoUsuario,
                    idPersona,
                    nombres,
                    apellidos,
                    rol
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}


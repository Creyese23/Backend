package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.LoginRequest;
import com.sena.creyese.dentvision_backend_springboot.dto.LoginResponse;
import com.sena.creyese.dentvision_backend_springboot.entity.Empleado;
import com.sena.creyese.dentvision_backend_springboot.entity.EmpleadoRol;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRolRepository;
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
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoRolRepository empleadoRolRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getDocumento(), loginRequest.getPassword())
            );

            String token = jwtUtil.generateToken(loginRequest.getDocumento());

            Empleado empleado = empleadoRepository.findByDocumento(loginRequest.getDocumento())
                    .orElse(null);

            if (empleado == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // Obtener el primer rol del empleado
            String rolNombre = null;
            List<EmpleadoRol> empleadoRoles = empleadoRolRepository.findByEmpleado_IdEmpleado(empleado.getIdEmpleado());
            if (!empleadoRoles.isEmpty()) {
                rolNombre = empleadoRoles.get(0).getRol().getNombreRol();
            }

            LoginResponse response = new LoginResponse(
                    token,
                    "Bearer",
                    empleado.getIdEmpleado(),
                    empleado.getNombres(),
                    empleado.getApellidos(),
                    rolNombre
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}


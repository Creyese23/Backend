package com.sena.creyese.dentvision_backend_springboot.security;

import com.sena.creyese.dentvision_backend_springboot.entity.Empleado;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String documento) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByDocumento(documento)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado con documento: " + documento));

        return org.springframework.security.core.userdetails.User
                .withUsername(empleado.getDocumento())
                .password(empleado.getPassword())
                .authorities(new ArrayList<>())
                .accountLocked(!empleado.getEstado().equals("ACTIVO"))
                .build();
    }
}

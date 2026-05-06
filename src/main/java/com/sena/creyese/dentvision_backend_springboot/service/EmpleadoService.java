package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Empleado;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Optional<Empleado> findByDocumento(String documento) {
        return empleadoRepository.findByDocumento(documento);
    }

    public Optional<Empleado> findByCorreo(String correo) {
        return empleadoRepository.findByCorreo(correo);
    }

    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado update(Long id, Empleado empleado) {
        if (empleadoRepository.existsById(id)) {
            empleado.setIdEmpleado(id);
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

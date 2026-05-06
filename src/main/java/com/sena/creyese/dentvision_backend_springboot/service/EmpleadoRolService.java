package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.EmpleadoRol;
import com.sena.creyese.dentvision_backend_springboot.repository.EmpleadoRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoRolService {
    
    @Autowired
    private EmpleadoRolRepository empleadoRolRepository;
    
    public EmpleadoRol save(EmpleadoRol empleadoRol) {
        return empleadoRolRepository.save(empleadoRol);
    }
    
    public Optional<EmpleadoRol> findById(Long id) {
        return empleadoRolRepository.findById(id);
    }
    
    public List<EmpleadoRol> findAll() {
        return empleadoRolRepository.findAll();
    }
    
    public List<EmpleadoRol> findByEmpleado(Long idEmpleado) {
        return empleadoRolRepository.findByEmpleado_IdEmpleado(idEmpleado);
    }
    
    public List<EmpleadoRol> findByRol(Long idRol) {
        return empleadoRolRepository.findByRol_IdRol(idRol);
    }
    
    public void deleteById(Long id) {
        empleadoRolRepository.deleteById(id);
    }
}

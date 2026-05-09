package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.EmpleadoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRolRepository extends JpaRepository<EmpleadoRol, Long> {
    List<EmpleadoRol> findByEmpleado_IdEmpleado(Long idEmpleado);
    List<EmpleadoRol> findByRol_IdRol(Long idRol);
    Optional<EmpleadoRol> findByEmpleado_IdEmpleadoAndRol_IdRol(Long idEmpleado, Long idRol);
}

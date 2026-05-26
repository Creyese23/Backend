package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByFechaHora(LocalDateTime fechaHora);
    List<Cita> findByPacienteIdUsuario(Long idPaciente);
    List<Cita> findByOdontologoIdUsuario(Long idEmpleado);
}

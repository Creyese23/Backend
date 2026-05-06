package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.CitaServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaServicioRepository extends JpaRepository<CitaServicio, Long> {
    List<CitaServicio> findByCita_IdCita(Long idCita);
    List<CitaServicio> findByServicio_IdServicio(Long idServicio);
}

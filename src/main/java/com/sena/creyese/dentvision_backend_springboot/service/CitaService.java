package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Cita;
import com.sena.creyese.dentvision_backend_springboot.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> findByFechaHora(LocalDateTime fechaHora) {
        return citaRepository.findByFechaHora(fechaHora);
    }

    public List<Cita> findByPaciente(Long idPaciente) {
        return citaRepository.findByPacienteIdPaciente(idPaciente);
    }

    public List<Cita> findByOdontologo(Long idOdontologo) {
        return citaRepository.findByOdontologoIdEmpleado(idOdontologo);
    }

    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita update(Long id, Cita cita) {
        if (citaRepository.existsById(id)) {
            cita.setIdCita(id);
            return citaRepository.save(cita);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

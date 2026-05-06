package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.CitaServicio;
import com.sena.creyese.dentvision_backend_springboot.repository.CitaServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServicioService {
    
    @Autowired
    private CitaServicioRepository citaServicioRepository;
    
    public CitaServicio save(CitaServicio citaServicio) {
        return citaServicioRepository.save(citaServicio);
    }
    
    public Optional<CitaServicio> findById(Long id) {
        return citaServicioRepository.findById(id);
    }
    
    public List<CitaServicio> findAll() {
        return citaServicioRepository.findAll();
    }
    
    public List<CitaServicio> findByCita(Long idCita) {
        return citaServicioRepository.findByCita_IdCita(idCita);
    }
    
    public List<CitaServicio> findByServicio(Long idServicio) {
        return citaServicioRepository.findByServicio_IdServicio(idServicio);
    }
    
    public void deleteById(Long id) {
        citaServicioRepository.deleteById(id);
    }
}

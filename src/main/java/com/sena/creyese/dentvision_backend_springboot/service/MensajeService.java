package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Mensaje;
import com.sena.creyese.dentvision_backend_springboot.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> findById(Long id) {
        return mensajeRepository.findById(id);
    }

    public List<Mensaje> findByConversacion(Long idConversacion) {
        return mensajeRepository.findByConversacionIdConversacionOrderByFechaHoraAsc(idConversacion);
    }

    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public Mensaje update(Long id, Mensaje mensaje) {
        if (mensajeRepository.existsById(id)) {
            mensaje.setIdMensaje(id);
            return mensajeRepository.save(mensaje);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (mensajeRepository.existsById(id)) {
            mensajeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Conversacion;
import com.sena.creyese.dentvision_backend_springboot.repository.ConversacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversacionService {

    @Autowired
    private ConversacionRepository conversacionRepository;

    public List<Conversacion> findAll() {
        return conversacionRepository.findAll();
    }

    public Optional<Conversacion> findById(Long id) {
        return conversacionRepository.findById(id);
    }

    public List<Conversacion> findByPaciente(Long idPaciente) {
        return conversacionRepository.findByPacienteIdPaciente(idPaciente);
    }

    public Conversacion save(Conversacion conversacion) {
        return conversacionRepository.save(conversacion);
    }

    public Conversacion update(Long id, Conversacion conversacion) {
        if (conversacionRepository.existsById(id)) {
            conversacion.setIdConversacion(id);
            return conversacionRepository.save(conversacion);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (conversacionRepository.existsById(id)) {
            conversacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

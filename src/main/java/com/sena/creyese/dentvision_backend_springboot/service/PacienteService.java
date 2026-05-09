package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Paciente;
import com.sena.creyese.dentvision_backend_springboot.entity.Usuario;
import com.sena.creyese.dentvision_backend_springboot.repository.PacienteRepository;
import com.sena.creyese.dentvision_backend_springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> findByDocumento(String documento) {
        return pacienteRepository.findByDocumento(documento);
    }

    public Optional<Paciente> findByEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente update(Long id, Paciente paciente) {
        if (pacienteRepository.existsById(id)) {
            paciente.setIdPaciente(id);
            return pacienteRepository.save(paciente);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Paciente saveWithUsuario(Paciente paciente, Usuario usuario) {
        Usuario savedUsuario = usuarioRepository.save(usuario);
        paciente.setUsuario(savedUsuario);
        return pacienteRepository.save(paciente);
    }
}

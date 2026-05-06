package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Entrega;
import com.sena.creyese.dentvision_backend_springboot.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public Optional<Entrega> findById(Long id) {
        return entregaRepository.findById(id);
    }

    public List<Entrega> findByOrden(Long idOrden) {
        return entregaRepository.findByOrdenIdOrden(idOrden);
    }

    public Entrega save(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Entrega update(Long id, Entrega entrega) {
        if (entregaRepository.existsById(id)) {
            entrega.setIdEntrega(id);
            return entregaRepository.save(entrega);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

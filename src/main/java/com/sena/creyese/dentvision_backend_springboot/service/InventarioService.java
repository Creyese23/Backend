package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Insumo;
import com.sena.creyese.dentvision_backend_springboot.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Insumo> findAll() {
        return inventarioRepository.findAll();
    }

    public Optional<Insumo> findById(Long id) {
        return inventarioRepository.findById(id);
    }

    public List<Insumo> findByEstado(String estado) {
        return inventarioRepository.findByEstado(estado);
    }

    public Insumo save(Insumo insumo) {
        return inventarioRepository.save(insumo);
    }

    public Insumo update(Long id, Insumo insumo) {
        if (inventarioRepository.existsById(id)) {
            insumo.setIdInsumo(id);
            return inventarioRepository.save(insumo);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

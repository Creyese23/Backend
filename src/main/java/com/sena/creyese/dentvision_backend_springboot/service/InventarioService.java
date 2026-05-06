package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Inventario;
import com.sena.creyese.dentvision_backend_springboot.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> findById(Long id) {
        return inventarioRepository.findById(id);
    }

    public List<Inventario> findByEstado(String estado) {
        return inventarioRepository.findByEstado(estado);
    }

    public List<Inventario> findByCantidadLessThan(Integer cantidad) {
        return inventarioRepository.findByCantidadLessThan(cantidad);
    }

    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario update(Long id, Inventario inventario) {
        if (inventarioRepository.existsById(id)) {
            inventario.setIdInsumo(id);
            return inventarioRepository.save(inventario);
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

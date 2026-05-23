package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Insumo;
import com.sena.creyese.dentvision_backend_springboot.enums.InventarioEstado;
import com.sena.creyese.dentvision_backend_springboot.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    public List<Insumo> findAll() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> findById(Long id) {
        return insumoRepository.findById(id);
    }

    public List<Insumo> findByEstado(InventarioEstado estado) {
        return insumoRepository.findByEstado(estado);
    }

    public Insumo save(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    public Insumo update(Long id, Insumo insumo) {
        if (insumoRepository.existsById(id)) {
            insumo.setIdInsumo(id);
            return insumoRepository.save(insumo);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (insumoRepository.existsById(id)) {
            insumoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

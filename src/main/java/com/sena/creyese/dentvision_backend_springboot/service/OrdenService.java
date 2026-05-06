package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Orden;
import com.sena.creyese.dentvision_backend_springboot.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public Optional<Orden> findById(Long id) {
        return ordenRepository.findById(id);
    }

    public List<Orden> findByProcedimiento(Long idProcedimiento) {
        return ordenRepository.findByProcedimientoIdProcedimiento(idProcedimiento);
    }

    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    public Orden update(Long id, Orden orden) {
        if (ordenRepository.existsById(id)) {
            orden.setIdOrden(id);
            return ordenRepository.save(orden);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (ordenRepository.existsById(id)) {
            ordenRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

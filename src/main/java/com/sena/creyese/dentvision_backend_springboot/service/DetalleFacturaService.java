package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.DetalleFactura;
import com.sena.creyese.dentvision_backend_springboot.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public List<DetalleFactura> findAll() {
        return detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFactura> findById(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public List<DetalleFactura> findByFactura(Long idFactura) {
        return detalleFacturaRepository.findByFacturaIdFactura(idFactura);
    }

    public DetalleFactura save(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    public DetalleFactura update(Long id, DetalleFactura detalleFactura) {
        if (detalleFacturaRepository.existsById(id)) {
            detalleFactura.setIdDetalle(id);
            return detalleFacturaRepository.save(detalleFactura);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (detalleFacturaRepository.existsById(id)) {
            detalleFacturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

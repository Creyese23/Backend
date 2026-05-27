package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Factura;
import com.sena.creyese.dentvision_backend_springboot.enums.EstadoPago;
import com.sena.creyese.dentvision_backend_springboot.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    public List<Factura> findByPaciente(Long idPaciente) {
        return facturaRepository.findByPacienteIdUsuario(idPaciente);
    }

    public List<Factura> findByEstadoPago(EstadoPago estadoPago) {
        return facturaRepository.findByEstadoPago(estadoPago);
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura update(Long id, Factura factura) {
        if (facturaRepository.existsById(id)) {
            factura.setIdFactura(id);
            return facturaRepository.save(factura);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

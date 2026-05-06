package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Pago;
import com.sena.creyese.dentvision_backend_springboot.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;
    
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }
    
    public Optional<Pago> findById(Long id) {
        return pagoRepository.findById(id);
    }
    
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }
    
    public List<Pago> findByFactura(Long idFactura) {
        return pagoRepository.findByFactura_IdFactura(idFactura);
    }
    
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }
}

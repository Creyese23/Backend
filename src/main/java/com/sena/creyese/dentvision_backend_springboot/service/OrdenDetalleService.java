package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.OrdenDetalle;
import com.sena.creyese.dentvision_backend_springboot.repository.OrdenDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenDetalleService {
    
    @Autowired
    private OrdenDetalleRepository ordenDetalleRepository;
    
    public OrdenDetalle save(OrdenDetalle ordenDetalle) {
        return ordenDetalleRepository.save(ordenDetalle);
    }
    
    public Optional<OrdenDetalle> findById(Long id) {
        return ordenDetalleRepository.findById(id);
    }
    
    public List<OrdenDetalle> findAll() {
        return ordenDetalleRepository.findAll();
    }
    
    public List<OrdenDetalle> findByOrden(Long idOrden) {
        return ordenDetalleRepository.findByOrden_IdOrden(idOrden);
    }
    
    public List<OrdenDetalle> findByServicio(Long idServicio) {
        return ordenDetalleRepository.findByServicio_IdServicio(idServicio);
    }
    
    public void deleteById(Long id) {
        ordenDetalleRepository.deleteById(id);
    }
}

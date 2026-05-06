package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.OrdenDetalleDTO;
import com.sena.creyese.dentvision_backend_springboot.entity.OrdenDetalle;
import com.sena.creyese.dentvision_backend_springboot.service.OrdenDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes-detalles")
public class OrdenDetalleController {
    
    @Autowired
    private OrdenDetalleService ordenDetalleService;
    
    @PostMapping
    public ResponseEntity<OrdenDetalle> create(@RequestBody OrdenDetalle ordenDetalle) {
        OrdenDetalle saved = ordenDetalleService.save(ordenDetalle);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrdenDetalle> getById(@PathVariable Long id) {
        Optional<OrdenDetalle> ordenDetalle = ordenDetalleService.findById(id);
        return ordenDetalle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<OrdenDetalle>> getAll() {
        List<OrdenDetalle> ordenDetalles = ordenDetalleService.findAll();
        return ResponseEntity.ok(ordenDetalles);
    }
    
    @GetMapping("/orden/{idOrden}")
    public ResponseEntity<List<OrdenDetalle>> getByOrden(@PathVariable Long idOrden) {
        List<OrdenDetalle> ordenDetalles = ordenDetalleService.findByOrden(idOrden);
        return ResponseEntity.ok(ordenDetalles);
    }
    
    @GetMapping("/servicio/{idServicio}")
    public ResponseEntity<List<OrdenDetalle>> getByServicio(@PathVariable Long idServicio) {
        List<OrdenDetalle> ordenDetalles = ordenDetalleService.findByServicio(idServicio);
        return ResponseEntity.ok(ordenDetalles);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrdenDetalle> update(@PathVariable Long id, @RequestBody OrdenDetalle ordenDetalle) {
        Optional<OrdenDetalle> existing = ordenDetalleService.findById(id);
        if (existing.isPresent()) {
            ordenDetalle.setIdOrdenDetalle(id);
            OrdenDetalle updated = ordenDetalleService.save(ordenDetalle);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordenDetalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

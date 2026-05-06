package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.CitaServicioDTO;
import com.sena.creyese.dentvision_backend_springboot.entity.CitaServicio;
import com.sena.creyese.dentvision_backend_springboot.service.CitaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas-servicios")
public class CitaServicioController {
    
    @Autowired
    private CitaServicioService citaServicioService;
    
    @PostMapping
    public ResponseEntity<CitaServicio> create(@RequestBody CitaServicio citaServicio) {
        CitaServicio saved = citaServicioService.save(citaServicio);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CitaServicio> getById(@PathVariable Long id) {
        Optional<CitaServicio> citaServicio = citaServicioService.findById(id);
        return citaServicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<CitaServicio>> getAll() {
        List<CitaServicio> citaServicios = citaServicioService.findAll();
        return ResponseEntity.ok(citaServicios);
    }
    
    @GetMapping("/cita/{idCita}")
    public ResponseEntity<List<CitaServicio>> getByCita(@PathVariable Long idCita) {
        List<CitaServicio> citaServicios = citaServicioService.findByCita(idCita);
        return ResponseEntity.ok(citaServicios);
    }
    
    @GetMapping("/servicio/{idServicio}")
    public ResponseEntity<List<CitaServicio>> getByServicio(@PathVariable Long idServicio) {
        List<CitaServicio> citaServicios = citaServicioService.findByServicio(idServicio);
        return ResponseEntity.ok(citaServicios);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CitaServicio> update(@PathVariable Long id, @RequestBody CitaServicio citaServicio) {
        Optional<CitaServicio> existing = citaServicioService.findById(id);
        if (existing.isPresent()) {
            citaServicio.setIdCitaServicio(id);
            CitaServicio updated = citaServicioService.save(citaServicio);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        citaServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

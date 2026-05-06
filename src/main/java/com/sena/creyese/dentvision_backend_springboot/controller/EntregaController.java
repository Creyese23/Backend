package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Entrega;
import com.sena.creyese.dentvision_backend_springboot.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public ResponseEntity<List<Entrega>> getAllEntregas() {
        List<Entrega> entregas = entregaService.findAll();
        return new ResponseEntity<>(entregas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> getEntregaById(@PathVariable Long id) {
        return entregaService.findById(id)
                .map(entrega -> new ResponseEntity<>(entrega, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/orden/{idOrden}")
    public ResponseEntity<List<Entrega>> getEntregasByOrden(@PathVariable Long idOrden) {
        List<Entrega> entregas = entregaService.findByOrden(idOrden);
        return new ResponseEntity<>(entregas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Entrega> createEntrega(@RequestBody Entrega entrega) {
        Entrega createdEntrega = entregaService.save(entrega);
        return new ResponseEntity<>(createdEntrega, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> updateEntrega(@PathVariable Long id, @RequestBody Entrega entrega) {
        Entrega updatedEntrega = entregaService.update(id, entrega);
        if (updatedEntrega != null) {
            return new ResponseEntity<>(updatedEntrega, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable Long id) {
        if (entregaService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

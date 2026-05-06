package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Orden;
import com.sena.creyese.dentvision_backend_springboot.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<List<Orden>> getAllOrdenes() {
        List<Orden> ordenes = ordenService.findAll();
        return new ResponseEntity<>(ordenes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Long id) {
        return ordenService.findById(id)
                .map(orden -> new ResponseEntity<>(orden, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/procedimiento/{idProcedimiento}")
    public ResponseEntity<List<Orden>> getOrdenesByProcedimiento(@PathVariable Long idProcedimiento) {
        List<Orden> ordenes = ordenService.findByProcedimiento(idProcedimiento);
        return new ResponseEntity<>(ordenes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Orden> createOrden(@RequestBody Orden orden) {
        Orden createdOrden = ordenService.save(orden);
        return new ResponseEntity<>(createdOrden, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> updateOrden(@PathVariable Long id, @RequestBody Orden orden) {
        Orden updatedOrden = ordenService.update(id, orden);
        if (updatedOrden != null) {
            return new ResponseEntity<>(updatedOrden, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable Long id) {
        if (ordenService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

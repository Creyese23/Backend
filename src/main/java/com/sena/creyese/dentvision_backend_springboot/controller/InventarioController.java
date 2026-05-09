package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Insumo;
import com.sena.creyese.dentvision_backend_springboot.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Insumo>> getAllInventario() {
        List<Insumo> inventario = inventarioService.findAll();
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insumo> getInventarioById(@PathVariable Long id) {
        return inventarioService.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Insumo>> getInventarioByEstado(@PathVariable String estado) {
        List<Insumo> inventario = inventarioService.findByEstado(estado);
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Insumo> createInventario(@RequestBody Insumo insumo) {
        Insumo createdInventario = inventarioService.save(insumo);
        return new ResponseEntity<>(createdInventario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insumo> updateInventario(@PathVariable Long id, @RequestBody Insumo insumo) {
        Insumo updatedInventario = inventarioService.update(id, insumo);
        if (updatedInventario != null) {
            return new ResponseEntity<>(updatedInventario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        if (inventarioService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

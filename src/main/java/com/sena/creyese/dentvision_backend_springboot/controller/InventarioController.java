package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Inventario;
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
    public ResponseEntity<List<Inventario>> getAllInventario() {
        List<Inventario> inventario = inventarioService.findAll();
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        return inventarioService.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Inventario>> getInventarioByEstado(@PathVariable String estado) {
        List<Inventario> inventario = inventarioService.findByEstado(estado);
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @GetMapping("/stock-bajo/{cantidad}")
    public ResponseEntity<List<Inventario>> getInventarioStockBajo(@PathVariable Integer cantidad) {
        List<Inventario> inventario = inventarioService.findByCantidadLessThan(cantidad);
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventario> createInventario(@RequestBody Inventario inventario) {
        Inventario createdInventario = inventarioService.save(inventario);
        return new ResponseEntity<>(createdInventario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        Inventario updatedInventario = inventarioService.update(id, inventario);
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

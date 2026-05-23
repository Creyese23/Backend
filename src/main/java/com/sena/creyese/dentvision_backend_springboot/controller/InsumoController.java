package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Insumo;
import com.sena.creyese.dentvision_backend_springboot.enums.InventarioEstado;
import com.sena.creyese.dentvision_backend_springboot.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public ResponseEntity<List<Insumo>> getAllInsumos() {
        List<Insumo> insumos = insumoService.findAll();
        return new ResponseEntity<>(insumos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insumo> getInsumoById(@PathVariable Long id) {
        return insumoService.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Insumo>> getInsumosByEstado(@PathVariable InventarioEstado estado) {
        List<Insumo> insumos = insumoService.findByEstado(estado);
        return new ResponseEntity<>(insumos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Insumo> createInsumo(@RequestBody Insumo insumo) {
        Insumo createdInsumo = insumoService.save(insumo);
        return new ResponseEntity<>(createdInsumo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insumo> updateInsumo(@PathVariable Long id, @RequestBody Insumo insumo) {
        Insumo updatedInsumo = insumoService.update(id, insumo);
        if (updatedInsumo != null) {
            return new ResponseEntity<>(updatedInsumo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsumo(@PathVariable Long id) {
        if (insumoService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

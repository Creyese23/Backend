package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Factura;
import com.sena.creyese.dentvision_backend_springboot.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.findAll();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        return facturaService.findById(id)
                .map(factura -> new ResponseEntity<>(factura, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Factura>> getFacturasByPaciente(@PathVariable Long idPaciente) {
        List<Factura> facturas = facturaService.findByPaciente(idPaciente);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @GetMapping("/estado/{estadoPago}")
    public ResponseEntity<List<Factura>> getFacturasByEstadoPago(@PathVariable String estadoPago) {
        List<Factura> facturas = facturaService.findByEstadoPago(estadoPago);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura createdFactura = facturaService.save(factura);
        return new ResponseEntity<>(createdFactura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        Factura updatedFactura = facturaService.update(id, factura);
        if (updatedFactura != null) {
            return new ResponseEntity<>(updatedFactura, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        if (facturaService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

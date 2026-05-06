package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.DetalleFactura;
import com.sena.creyese.dentvision_backend_springboot.service.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-factura")
@CrossOrigin(origins = "*")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public ResponseEntity<List<DetalleFactura>> getAllDetallesFactura() {
        List<DetalleFactura> detalles = detalleFacturaService.findAll();
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getDetalleFacturaById(@PathVariable Long id) {
        return detalleFacturaService.findById(id)
                .map(detalle -> new ResponseEntity<>(detalle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/factura/{idFactura}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByFactura(@PathVariable Long idFactura) {
        List<DetalleFactura> detalles = detalleFacturaService.findByFactura(idFactura);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetalleFactura> createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        DetalleFactura createdDetalle = detalleFacturaService.save(detalleFactura);
        return new ResponseEntity<>(createdDetalle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(@PathVariable Long id, @RequestBody DetalleFactura detalleFactura) {
        DetalleFactura updatedDetalle = detalleFacturaService.update(id, detalleFactura);
        if (updatedDetalle != null) {
            return new ResponseEntity<>(updatedDetalle, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable Long id) {
        if (detalleFacturaService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

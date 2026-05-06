package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.PagoDTO;
import com.sena.creyese.dentvision_backend_springboot.entity.Pago;
import com.sena.creyese.dentvision_backend_springboot.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    
    @Autowired
    private PagoService pagoService;
    
    @PostMapping
    public ResponseEntity<Pago> create(@RequestBody Pago pago) {
        Pago saved = pagoService.save(pago);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pago> getById(@PathVariable Long id) {
        Optional<Pago> pago = pagoService.findById(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Pago>> getAll() {
        List<Pago> pagos = pagoService.findAll();
        return ResponseEntity.ok(pagos);
    }
    
    @GetMapping("/factura/{idFactura}")
    public ResponseEntity<List<Pago>> getByFactura(@PathVariable Long idFactura) {
        List<Pago> pagos = pagoService.findByFactura(idFactura);
        return ResponseEntity.ok(pagos);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Long id, @RequestBody Pago pago) {
        Optional<Pago> existing = pagoService.findById(id);
        if (existing.isPresent()) {
            pago.setIdPago(id);
            Pago updated = pagoService.save(pago);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

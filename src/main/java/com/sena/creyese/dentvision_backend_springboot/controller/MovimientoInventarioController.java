package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.MovimientoInventario;
import com.sena.creyese.dentvision_backend_springboot.service.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos-inventario")
@CrossOrigin(origins = "*")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @GetMapping
    public ResponseEntity<List<MovimientoInventario>> getAllMovimientos() {
        List<MovimientoInventario> movimientos = movimientoInventarioService.findAll();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> getMovimientoById(@PathVariable Long id) {
        return movimientoInventarioService.findById(id)
                .map(movimiento -> new ResponseEntity<>(movimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/insumo/{idInsumo}")
    public ResponseEntity<List<MovimientoInventario>> getMovimientosByInsumo(@PathVariable Long idInsumo) {
        List<MovimientoInventario> movimientos = movimientoInventarioService.findByInsumo(idInsumo);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipoMovimiento}")
    public ResponseEntity<List<MovimientoInventario>> getMovimientosByTipo(@PathVariable String tipoMovimiento) {
        List<MovimientoInventario> movimientos = movimientoInventarioService.findByTipoMovimiento(tipoMovimiento);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovimientoInventario> createMovimiento(@RequestBody MovimientoInventario movimiento) {
        MovimientoInventario createdMovimiento = movimientoInventarioService.save(movimiento);
        return new ResponseEntity<>(createdMovimiento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventario> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoInventario movimiento) {
        MovimientoInventario updatedMovimiento = movimientoInventarioService.update(id, movimiento);
        if (updatedMovimiento != null) {
            return new ResponseEntity<>(updatedMovimiento, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        if (movimientoInventarioService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Procedimiento;
import com.sena.creyese.dentvision_backend_springboot.service.ProcedimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedimientos")
@CrossOrigin(origins = "*")
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService procedimientoService;

    @GetMapping
    public ResponseEntity<List<Procedimiento>> getAllProcedimientos() {
        List<Procedimiento> procedimientos = procedimientoService.findAll();
        return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedimiento> getProcedimientoById(@PathVariable Long id) {
        return procedimientoService.findById(id)
                .map(procedimiento -> new ResponseEntity<>(procedimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cita/{idCita}")
    public ResponseEntity<List<Procedimiento>> getProcedimientosByCita(@PathVariable Long idCita) {
        List<Procedimiento> procedimientos = procedimientoService.findByCita(idCita);
        return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }

    @GetMapping("/tecnico/{idTecnico}")
    public ResponseEntity<List<Procedimiento>> getProcedimientosByTecnico(@PathVariable Long idTecnico) {
        List<Procedimiento> procedimientos = procedimientoService.findByTecnico(idTecnico);
        return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Procedimiento> createProcedimiento(@RequestBody Procedimiento procedimiento) {
        Procedimiento createdProcedimiento = procedimientoService.save(procedimiento);
        return new ResponseEntity<>(createdProcedimiento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimiento> updateProcedimiento(@PathVariable Long id, @RequestBody Procedimiento procedimiento) {
        Procedimiento updatedProcedimiento = procedimientoService.update(id, procedimiento);
        if (updatedProcedimiento != null) {
            return new ResponseEntity<>(updatedProcedimiento, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedimiento(@PathVariable Long id) {
        if (procedimientoService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

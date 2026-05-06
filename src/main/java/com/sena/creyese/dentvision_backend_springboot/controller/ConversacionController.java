package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Conversacion;
import com.sena.creyese.dentvision_backend_springboot.service.ConversacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversaciones")
@CrossOrigin(origins = "*")
public class ConversacionController {

    @Autowired
    private ConversacionService conversacionService;

    @GetMapping
    public ResponseEntity<List<Conversacion>> getAllConversaciones() {
        List<Conversacion> conversaciones = conversacionService.findAll();
        return new ResponseEntity<>(conversaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversacion> getConversacionById(@PathVariable Long id) {
        return conversacionService.findById(id)
                .map(conversacion -> new ResponseEntity<>(conversacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Conversacion>> getConversacionesByPaciente(@PathVariable Long idPaciente) {
        List<Conversacion> conversaciones = conversacionService.findByPaciente(idPaciente);
        return new ResponseEntity<>(conversaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Conversacion> createConversacion(@RequestBody Conversacion conversacion) {
        Conversacion createdConversacion = conversacionService.save(conversacion);
        return new ResponseEntity<>(createdConversacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conversacion> updateConversacion(@PathVariable Long id, @RequestBody Conversacion conversacion) {
        Conversacion updatedConversacion = conversacionService.update(id, conversacion);
        if (updatedConversacion != null) {
            return new ResponseEntity<>(updatedConversacion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversacion(@PathVariable Long id) {
        if (conversacionService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

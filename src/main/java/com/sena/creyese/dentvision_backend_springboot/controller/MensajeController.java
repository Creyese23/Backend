package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.entity.Mensaje;
import com.sena.creyese.dentvision_backend_springboot.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public ResponseEntity<List<Mensaje>> getAllMensajes() {
        List<Mensaje> mensajes = mensajeService.findAll();
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        return mensajeService.findById(id)
                .map(mensaje -> new ResponseEntity<>(mensaje, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/conversacion/{idConversacion}")
    public ResponseEntity<List<Mensaje>> getMensajesByConversacion(@PathVariable Long idConversacion) {
        List<Mensaje> mensajes = mensajeService.findByConversacion(idConversacion);
        return new ResponseEntity<>(mensajes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mensaje> createMensaje(@RequestBody Mensaje mensaje) {
        Mensaje createdMensaje = mensajeService.save(mensaje);
        return new ResponseEntity<>(createdMensaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> updateMensaje(@PathVariable Long id, @RequestBody Mensaje mensaje) {
        Mensaje updatedMensaje = mensajeService.update(id, mensaje);
        if (updatedMensaje != null) {
            return new ResponseEntity<>(updatedMensaje, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Long id) {
        if (mensajeService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

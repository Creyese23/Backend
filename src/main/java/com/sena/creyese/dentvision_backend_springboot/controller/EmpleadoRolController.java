package com.sena.creyese.dentvision_backend_springboot.controller;

import com.sena.creyese.dentvision_backend_springboot.dto.EmpleadoRolDTO;
import com.sena.creyese.dentvision_backend_springboot.entity.EmpleadoRol;
import com.sena.creyese.dentvision_backend_springboot.service.EmpleadoRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados-roles")
public class EmpleadoRolController {
    
    @Autowired
    private EmpleadoRolService empleadoRolService;
    
    @PostMapping
    public ResponseEntity<EmpleadoRol> create(@RequestBody EmpleadoRol empleadoRol) {
        EmpleadoRol saved = empleadoRolService.save(empleadoRol);
        return ResponseEntity.ok(saved);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoRol> getById(@PathVariable Long id) {
        Optional<EmpleadoRol> empleadoRol = empleadoRolService.findById(id);
        return empleadoRol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<EmpleadoRol>> getAll() {
        List<EmpleadoRol> empleadoRoles = empleadoRolService.findAll();
        return ResponseEntity.ok(empleadoRoles);
    }
    
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<EmpleadoRol>> getByEmpleado(@PathVariable Long idEmpleado) {
        List<EmpleadoRol> empleadoRoles = empleadoRolService.findByEmpleado(idEmpleado);
        return ResponseEntity.ok(empleadoRoles);
    }
    
    @GetMapping("/rol/{idRol}")
    public ResponseEntity<List<EmpleadoRol>> getByRol(@PathVariable Long idRol) {
        List<EmpleadoRol> empleadoRoles = empleadoRolService.findByRol(idRol);
        return ResponseEntity.ok(empleadoRoles);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoRol> update(@PathVariable Long id, @RequestBody EmpleadoRol empleadoRol) {
        Optional<EmpleadoRol> existing = empleadoRolService.findById(id);
        if (existing.isPresent()) {
            empleadoRol.setIdEmpleadoRol(id);
            EmpleadoRol updated = empleadoRolService.save(empleadoRol);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empleadoRolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

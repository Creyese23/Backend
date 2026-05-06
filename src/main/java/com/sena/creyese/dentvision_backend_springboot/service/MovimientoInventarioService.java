package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.MovimientoInventario;
import com.sena.creyese.dentvision_backend_springboot.repository.MovimientoInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    public List<MovimientoInventario> findAll() {
        return movimientoInventarioRepository.findAll();
    }

    public Optional<MovimientoInventario> findById(Long id) {
        return movimientoInventarioRepository.findById(id);
    }

    public List<MovimientoInventario> findByInsumo(Long idInsumo) {
        return movimientoInventarioRepository.findByInventarioIdInsumo(idInsumo);
    }

    public List<MovimientoInventario> findByTipoMovimiento(String tipoMovimiento) {
        return movimientoInventarioRepository.findByTipoMovimiento(tipoMovimiento);
    }

    public MovimientoInventario save(MovimientoInventario movimientoInventario) {
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    public MovimientoInventario update(Long id, MovimientoInventario movimientoInventario) {
        if (movimientoInventarioRepository.existsById(id)) {
            movimientoInventario.setIdMovimiento(id);
            return movimientoInventarioRepository.save(movimientoInventario);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (movimientoInventarioRepository.existsById(id)) {
            movimientoInventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

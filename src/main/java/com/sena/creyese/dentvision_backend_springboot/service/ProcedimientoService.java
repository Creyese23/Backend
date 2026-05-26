package com.sena.creyese.dentvision_backend_springboot.service;

import com.sena.creyese.dentvision_backend_springboot.entity.Procedimiento;
import com.sena.creyese.dentvision_backend_springboot.repository.ProcedimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedimientoService {

    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    public List<Procedimiento> findAll() {
        return procedimientoRepository.findAll();
    }

    public Optional<Procedimiento> findById(Long id) {
        return procedimientoRepository.findById(id);
    }

    public List<Procedimiento> findByCita(Long idCita) {
        return procedimientoRepository.findByCitaIdCita(idCita);
    }

    public List<Procedimiento> findByTecnico(Long idTecnico) {
        return procedimientoRepository.findByTecnicoIdUsuario(idTecnico);
    }

    public Procedimiento save(Procedimiento procedimiento) {
        return procedimientoRepository.save(procedimiento);
    }

    public Procedimiento update(Long id, Procedimiento procedimiento) {
        if (procedimientoRepository.existsById(id)) {
            procedimiento.setIdProcedimiento(id);
            return procedimientoRepository.save(procedimiento);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (procedimientoRepository.existsById(id)) {
            procedimientoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

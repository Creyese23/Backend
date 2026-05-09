package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.MovimientoInventario;
import com.sena.creyese.dentvision_backend_springboot.enums.TipoMovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    List<MovimientoInventario> findByInventorIdInsumo(Long idInsumo);
    List<MovimientoInventario> findByTipoMovimiento(TipoMovimientoInventario tipoMovimiento);
}

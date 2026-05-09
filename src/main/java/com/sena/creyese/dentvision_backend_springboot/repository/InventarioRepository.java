package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.Insumo;
import com.sena.creyese.dentvision_backend_springboot.enums.InventarioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByEstado(InventarioEstado estado);
}

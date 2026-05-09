package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDocumento(String documento);

    @Query("SELECT e FROM Empleado e JOIN e.usuario u WHERE u.email = :email")
    Optional<Empleado> findByEmail(@Param("email") String email);
}

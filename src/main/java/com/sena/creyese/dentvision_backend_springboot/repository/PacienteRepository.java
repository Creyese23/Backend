package com.sena.creyese.dentvision_backend_springboot.repository;

import com.sena.creyese.dentvision_backend_springboot.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDocumento(String documento);

    @Query("SELECT p FROM Paciente p JOIN p.usuario u WHERE u.email = :email")
    Optional<Paciente> findByEmail(@Param("email") String email);
}

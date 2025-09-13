package com.example.clinica.repository;

import com.example.clinica.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpfValue(String cpf);

    Optional<Paciente> findByNomeIgnoreCase(String nome);

    List<Paciente> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCpfValue(String cpf);

    boolean existsByEmailValue(String email);
    Optional<Paciente> findByEmailValue(String email);

}

package com.example.clinica.repository;

import com.example.clinica.domain.model.StatusConsulta;
import com.example.clinica.domain.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoId(Long medicoId);
    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByStatus(StatusConsulta status);

    List<Consulta> findByMedicoIdAndDataHoraBetween(Long medicoId, LocalDateTime start, LocalDateTime end);

    Optional<Consulta> findById(Long id);
}
package com.example.clinica.service;

import com.example.clinica.domain.model.Consulta;
import com.example.clinica.domain.model.StatusConsulta;
import com.example.clinica.domain.model.Paciente;
import com.example.clinica.domain.model.Medico;
import com.example.clinica.dto.consulta.ConsultaCreateDTO;
import com.example.clinica.dto.consulta.ConsultaResponseDTO;
import com.example.clinica.dto.consulta.ConsultaEditDTO;
import com.example.clinica.repository.ConsultaRepository;
import com.example.clinica.repository.PacienteRepository;
import com.example.clinica.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    @Transactional
    public ConsultaResponseDTO criar(ConsultaCreateDTO dto) {
        Paciente paciente = pacienteRepo.findById(dto.pacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        Medico medico = medicoRepo.findById(dto.medicoId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .status(StatusConsulta.AGENDADA)
                .dataHora(dto.dataHora())
                .build();

        Consulta salvo = consultaRepo.save(consulta);

        return new ConsultaResponseDTO(
                salvo.getId(),
                salvo.getPaciente().getNome(),
                salvo.getMedico().getNome(),
                salvo.getStatus().name(),
                salvo.getDataHora()
        );
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> listar() {
        return consultaRepo.findAll().stream()
                .map(c -> new ConsultaResponseDTO(
                        c.getId(),
                        c.getPaciente().getNome(),
                        c.getMedico().getNome(),
                        c.getStatus().name(),
                        c.getDataHora()
                ))
                .toList();
    }

    @Transactional
    public ConsultaResponseDTO editar(Long id, ConsultaEditDTO dto) {
        Consulta consulta = consultaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        Paciente paciente = pacienteRepo.findById(dto.pacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        Medico medico = medicoRepo.findById(dto.medicoId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setStatus(StatusConsulta.valueOf(dto.status()));
        consulta.setDataHora(dto.dataHora());

        Consulta salvo = consultaRepo.save(consulta);

        return new ConsultaResponseDTO(
                salvo.getId(),
                salvo.getPaciente().getNome(),
                salvo.getMedico().getNome(),
                salvo.getStatus().name(),
                salvo.getDataHora()
        );
    }

    @Transactional
    public void deletar(Long id) {
        if (!consultaRepo.existsById(id)) {
            throw new IllegalArgumentException("Consulta não encontrada");
        }
        consultaRepo.deleteById(id);
    }

}

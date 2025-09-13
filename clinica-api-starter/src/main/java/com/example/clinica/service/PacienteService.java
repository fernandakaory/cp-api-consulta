package com.example.clinica.service;

import com.example.clinica.domain.model.Paciente;
import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.Email;
import com.example.clinica.dto.paciente.PacienteCreateDTO;
import com.example.clinica.dto.paciente.PacienteResponseDTO;
import com.example.clinica.dto.paciente.PacienteEditDTO;
import com.example.clinica.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repo;

    @Transactional
    public Long criar(PacienteCreateDTO dto) {
        repo.findByCpfValue(dto.cpf()).ifPresent(p -> {
            throw new IllegalArgumentException("CPF já cadastrado");
        });
        Paciente paciente = Paciente.builder()
                .nome(dto.nome())
                .cpf(new Cpf(dto.cpf()))
                .email(new Email(dto.email()))
                .build();
        return repo.save(paciente).getId();
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listar() {
        return repo.findAll().stream()
                .map(p -> new PacienteResponseDTO(
                        p.getId(), p.getNome(),
                        p.getCpf().getValue(), p.getEmail().getValue()))
                .toList();
    }

    @Transactional
    public void editar(Long id, PacienteEditDTO dto) {
        Paciente paciente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        paciente.setNome(dto.nome());
        paciente.setCpf(new Cpf(dto.cpf()));
        paciente.setEmail(new Email(dto.email()));

        repo.save(paciente);
    }
}
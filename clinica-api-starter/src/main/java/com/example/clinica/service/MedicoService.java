package com.example.clinica.service;

import com.example.clinica.domain.model.Medico;
import com.example.clinica.dto.medico.MedicoCreateDTO;
import com.example.clinica.dto.medico.MedicoResponseDTO;
import com.example.clinica.dto.medico.MedicoEditDTO;
import com.example.clinica.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repo;

    @Transactional
    public Long criar(MedicoCreateDTO dto) {
        repo.findByCrm(dto.crm()).ifPresent(m -> {
            throw new IllegalArgumentException("CRM já cadastrado");
        });
        Medico medico = Medico.builder()
                .nome(dto.nome())
                .crm(dto.crm())
                .build();
        return repo.save(medico).getId();
    }

    @Transactional(readOnly = true)
    public List<MedicoResponseDTO> listar() {
        return repo.findAll().stream()
                .map(m -> new MedicoResponseDTO(m.getId(), m.getNome(), m.getCrm()))
                .toList();
    }

    @Transactional
    public void editar(Long id, MedicoEditDTO dto) {
        Medico medico = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        medico.setNome(dto.nome());
        medico.setCrm(dto.crm());

        repo.save(medico);
    }
}
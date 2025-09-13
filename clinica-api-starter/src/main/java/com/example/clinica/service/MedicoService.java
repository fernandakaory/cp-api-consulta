package com.example.clinica.service;

import com.example.clinica.domain.model.Medico;
import com.example.clinica.dto.medico.MedicoCreateDTO;
import com.example.clinica.dto.medico.MedicoResponseDTO;
import com.example.clinica.dto.medico.MedicoEditDTO;
import com.example.clinica.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository repo;

    @Transactional
    public MedicoResponseDTO criar(MedicoCreateDTO dto) {
        // Verifica se CRM já existe
        repo.findByCrm(dto.crm()).ifPresent(m -> {
            throw new IllegalArgumentException("CRM já cadastrado");
        });

        // Cria médico
        Medico medico = Medico.builder()
                .nome(dto.nome())
                .crm(dto.crm())
                .build();

        // Salva no banco
        Medico salvo = repo.save(medico);

        // Converte para DTO de resposta
        return new MedicoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getCrm()
        );
    }

    @Transactional(readOnly = true)
    public List<MedicoResponseDTO> listar() {
        return repo.findAll().stream()
                .map(m -> new MedicoResponseDTO(
                        m.getId(),
                        m.getNome(),
                        m.getCrm()))
                .toList();
    }

    @Transactional
    public MedicoResponseDTO editar(Long id, MedicoEditDTO dto) {
        // Busca médico existente
        Medico medico = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        // Atualiza campos
        medico.setNome(dto.nome());
        medico.setCrm(dto.crm());

        // Salva no banco
        Medico salvo = repo.save(medico);

        // Converte para DTO de resposta
        return new MedicoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getCrm()
        );
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Médico não encontrado");
        }
        repo.deleteById(id);
    }

}

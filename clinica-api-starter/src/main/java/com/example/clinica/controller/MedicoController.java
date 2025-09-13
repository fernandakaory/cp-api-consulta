package com.example.clinica.controller;

import com.example.clinica.dto.medico.MedicoCreateDTO;
import com.example.clinica.dto.medico.MedicoResponseDTO;
import com.example.clinica.dto.medico.MedicoEditDTO;
import com.example.clinica.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @Operation(summary = "Cria um médico")
    @ApiResponse(responseCode = "201", description = "Médico criado")
    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody MedicoCreateDTO dto) {
        Long id = service.criar(dto);
        return ResponseEntity.created(URI.create("/medicos/" + id)).build();
    }

    @Operation(summary = "Lista médicos")
    @GetMapping
    public List<MedicoResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca médico por id")
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.listar().stream()
                .filter(m -> m.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edita um médico")
    @ApiResponse(responseCode = "204", description = "Médico editado")
    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id, @Valid @RequestBody MedicoEditDTO dto) {
        service.editar(id, dto);
        return ResponseEntity.noContent().build();
    }
}
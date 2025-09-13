package com.example.clinica.controller;

import com.example.clinica.dto.paciente.PacienteCreateDTO;
import com.example.clinica.dto.paciente.PacienteResponseDTO;
import com.example.clinica.dto.paciente.PacienteEditDTO;
import com.example.clinica.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @Operation(summary = "Cria um paciente")
    @ApiResponse(responseCode = "201", description = "Criado")
    @PostMapping
    //pacienteresponse
    public ResponseEntity<Void> criar(@Valid @RequestBody PacienteCreateDTO dto) {
        Long id = service.criar(dto);
        return ResponseEntity.created(URI.create("/pacientes/" + id)).build();
    }

    @Operation(summary = "Lista pacientes")
    @GetMapping
    public List<PacienteResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca paciente por id")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.listar()
                .stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edita um paciente")
    @ApiResponse(responseCode = "204", description = "Paciente editado")
    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id, @Valid @RequestBody PacienteEditDTO dto) {
        service.editar(id, dto);
        return ResponseEntity.noContent().build();
    }
}
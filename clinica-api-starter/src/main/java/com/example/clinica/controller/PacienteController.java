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
    @ApiResponse(responseCode = "201", description = "Paciente criado")
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criar(@Valid @RequestBody PacienteCreateDTO dto) {
        PacienteResponseDTO pacienteCriado = service.criar(dto);
        return ResponseEntity
                .created(URI.create("/pacientes/" + pacienteCriado.id()))
                .body(pacienteCriado);
    }

    @Operation(summary = "Lista todos os pacientes")
    @GetMapping
    public List<PacienteResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca paciente por ID")
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
    @ApiResponse(responseCode = "200", description = "Paciente editado")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> editar(@PathVariable Long id,
                                                      @Valid @RequestBody PacienteEditDTO dto) {
        PacienteResponseDTO pacienteAtualizado = service.editar(id, dto);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um paciente")
    @ApiResponse(responseCode = "204", description = "Paciente deletado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}

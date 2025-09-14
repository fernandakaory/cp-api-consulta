package com.example.clinica.controller;

import com.example.clinica.dto.consulta.ConsultaCreateDTO;
import com.example.clinica.dto.consulta.ConsultaResponseDTO;
import com.example.clinica.dto.consulta.ConsultaEditDTO;
import com.example.clinica.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @Operation(summary = "Cria uma consulta")
    @ApiResponse(responseCode = "201", description = "Consulta criada")
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> criar(@Valid @RequestBody ConsultaCreateDTO dto) {
        ConsultaResponseDTO consultaCriada = service.criar(dto);
        return ResponseEntity
                .created(URI.create("/consultas/" + consultaCriada.id()))
                .body(consultaCriada);
    }

    @Operation(summary = "Lista consultas")
    @GetMapping
    public List<ConsultaResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca consulta por id")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.listar().stream()
                .filter(c -> c.id().equals(id))       // record usa id()
                .findFirst()
                .map(c -> ResponseEntity.ok(c))       // lambda evita ambiguidades
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edita uma consulta")
    @ApiResponse(responseCode = "200", description = "Consulta editada")
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> editar(@PathVariable Long id,
                                                      @Valid @RequestBody ConsultaEditDTO dto) {
        ConsultaResponseDTO consultaAtualizada = service.editar(id, dto);
        return ResponseEntity.ok(consultaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma consulta")
    @ApiResponse(responseCode = "204", description = "Consulta deletada")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista consultas por médico")
    @GetMapping("/medico/{medicoId}")
    public List<ConsultaResponseDTO> listarPorMedico(@PathVariable Long medicoId) {
        return service.listarPorMedico(medicoId);
    }

    @Operation(summary = "Lista consultas por paciente")
    @GetMapping("/paciente/{pacienteId}")
    public List<ConsultaResponseDTO> listarPorPaciente(@PathVariable Long pacienteId) {
        return service.listarPorPaciente(pacienteId);
    }


}

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
    public ResponseEntity<Void> criar(@Valid @RequestBody ConsultaCreateDTO dto) {
        Long id = service.criar(dto);
        return ResponseEntity.created(URI.create("/consultas/" + id)).build();
    }

    @Operation(summary = "Lista consultas")
    @GetMapping
    public List<ConsultaResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca consulta por id")
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.listar()
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edita uma consulta")
    @ApiResponse(responseCode = "204", description = "Consulta editada")
    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id, @Valid @RequestBody ConsultaEditDTO dto) {
        service.editar(id, dto);
        return ResponseEntity.noContent().build();
    }
}
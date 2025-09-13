package com.example.clinica.controller;

import com.example.clinica.dto.medico.MedicoCreateDTO;
import com.example.clinica.dto.medico.MedicoResponseDTO;
import com.example.clinica.dto.medico.MedicoEditDTO;
import com.example.clinica.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @Operation(summary = "Cria um médico")
    @ApiResponse(responseCode = "201", description = "Médico criado")
    @PostMapping
    public ResponseEntity<MedicoResponseDTO> criar(@Valid @RequestBody MedicoCreateDTO dto) {
        MedicoResponseDTO medicoCriado = service.criar(dto);
        return ResponseEntity
                .created(URI.create("/medicos/" + medicoCriado.id()))
                .body(medicoCriado);
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
                .filter(m -> m.id().equals(id))      // record usa id()
                .findFirst()
                .map(m -> ResponseEntity.ok(m))       // lambda
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edita um médico")
    @ApiResponse(responseCode = "200", description = "Médico editado")
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> editar(@PathVariable Long id,
                                                    @Valid @RequestBody MedicoEditDTO dto) {
        MedicoResponseDTO medicoAtualizado = service.editar(id, dto);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um médico")
    @ApiResponse(responseCode = "204", description = "Médico deletado")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

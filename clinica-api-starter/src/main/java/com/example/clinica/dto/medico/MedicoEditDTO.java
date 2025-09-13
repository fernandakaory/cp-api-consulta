package com.example.clinica.dto.medico;

import jakarta.validation.constraints.NotBlank;

public record MedicoEditDTO(
        @NotBlank String nome,
        @NotBlank String crm
) {}
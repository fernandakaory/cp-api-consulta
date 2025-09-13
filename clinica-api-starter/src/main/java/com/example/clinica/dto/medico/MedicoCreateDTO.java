package com.example.clinica.dto.medico;

import jakarta.validation.constraints.NotBlank;

public record MedicoCreateDTO(
        @NotBlank String nome,
        @NotBlank String crm
) {}
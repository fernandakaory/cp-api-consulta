package com.example.clinica.dto.paciente;

import jakarta.validation.constraints.*;

public record PacienteCreateDTO(
        @NotBlank String nome,
        @NotBlank @Pattern(regexp="\\d{11}") String cpf,
        @NotBlank @Email String email
) {}
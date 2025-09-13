package com.example.clinica.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record PacienteEditDTO(
        @NotBlank String nome,
        @NotBlank @Pattern(regexp="\\d{11}") String cpf,
        @NotBlank @Email String email
) {}
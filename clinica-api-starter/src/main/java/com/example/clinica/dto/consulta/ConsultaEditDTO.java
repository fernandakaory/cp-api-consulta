package com.example.clinica.dto.consulta;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ConsultaEditDTO(
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        @NotNull String status,
        @NotNull LocalDateTime dataHora
) {}
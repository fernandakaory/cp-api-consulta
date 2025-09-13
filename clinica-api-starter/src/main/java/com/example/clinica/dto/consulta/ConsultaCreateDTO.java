package com.example.clinica.dto.consulta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;

public record ConsultaCreateDTO(
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        @NotNull @Future LocalDateTime dataHora
) {}
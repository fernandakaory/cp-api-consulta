package com.example.clinica.dto.consulta;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        String pacienteNome,
        String medicoNome,
        String status,
        LocalDateTime dataHora
) {}
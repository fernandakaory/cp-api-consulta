package com.example.clinica.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<>();
}

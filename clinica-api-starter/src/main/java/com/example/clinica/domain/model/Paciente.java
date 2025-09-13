package com.example.clinica.domain.model;

import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.Email;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Cpf cpf;

    @Embedded
    private Email email;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();
}

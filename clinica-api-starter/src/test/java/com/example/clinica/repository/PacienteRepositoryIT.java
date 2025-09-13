package com.example.clinica.repository;

import com.example.clinica.domain.model.Paciente;
import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class PacienteRepositoryIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("clinica")
            .withUsername("clinica")
            .withPassword("secret");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", postgres::getJdbcUrl);
        r.add("spring.datasource.username", postgres::getUsername);
        r.add("spring.datasource.password", postgres::getPassword);
        r.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
    }

    @Autowired
    PacienteRepository repo;

    @Test
    void deveSalvarEEncontrarPorCpf() {
        Paciente p = Paciente.builder()
                .nome("Ana")
                .cpf(new Cpf("12345678901"))
                .email(new Email("ana@example.com"))
                .build();
        repo.save(p);
        assertTrue(repo.findByCpfValue("12345678901").isPresent());
    }
}

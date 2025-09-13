# Clínica API — Spring Boot (SOA — Prof. Salatiel Luz Marinho)

## Requisitos
- Java 21
- Maven 3.9+
- Docker (para rodar testes com Testcontainers) e PostgreSQL local (para desenvolvimento)

## Como rodar
```bash
mvn spring-boot:run
# Swagger UI:
# http://localhost:8080/swagger-ui.html
```

## Banco de Desenvolvimento
A aplicação espera um Postgres local:
- URL: `jdbc:postgresql://localhost:5432/clinica`
- user: `clinica`
- password: `secret`

Use o script do Flyway (automaticamente aplicado) em `src/main/resources/db/migration/V1__init.sql`.

## Endpoints iniciais
- `POST /pacientes` — cria paciente (201)
- `GET  /pacientes` — lista paginada

## Atividade dos estudantes
Implemente o CRUD de **Médico** com validações e documentação OpenAPI.

## Testes de Integração
```bash
mvn -q -DskipTests=false test
```
Os testes utilizam **Testcontainers** com uma instância PostgreSQL efêmera.

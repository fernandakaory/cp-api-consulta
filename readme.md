# API de Agendamento de Consultas

Este projeto é uma API para gerenciamento de **consultas médicas**, com cadastro de **pacientes**, **médicos** e **consultas**.  
A documentação interativa está disponível via **Swagger UI**:  
👉 `http://localhost:8080/swagger-ui.html`

---

## Estrutura da API

A API é dividida em três recursos principais:
- **Pacientes**
- **Médicos**
- **Consultas**

Cada recurso possui endpoints para **Cadastrar (POST)**, **Listar (GET)**, **Editar (PUT)** e **Deletar (DELETE)**.

---

## Pacientes

### Cadastrar paciente
`POST /pacientes`

- **Descrição:** Registra um novo paciente.
- **Body (exemplo):**
```json
{
  "nome": "Maria Silva",
  "cpf": "12345678900",
  "email": "maria@email.com"
}

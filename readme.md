# API de Agendamento de Consultas

Este projeto é uma API para gerenciamento de **consultas médicas**, com cadastro de **pacientes**, **médicos** e **consultas**.  
A documentação interativa está disponível via **Swagger UI**:  
- Link Swagger - http://localhost:8080/swagger-ui.html
- Link Youtube - https://youtu.be/3oeHagna33A

---
## Sugestões de Evoluções

1. **Implementar busca de consultas por CPF do paciente** (`GET`)  
   Funcionalidade que permite a recuperação de consultas associadas a um paciente específico utilizando seu CPF como parâmetro.

2. **Implementar busca de consultas por CRM do médico** (`GET`)  
   Funcionalidade que permite a recuperação de consultas associadas a um médico específico utilizando seu CRM como parâmetro.

3. **Busca de consultas por ID do paciente** (`GET`) – *Funcionalidade já implementada*  
   Esta funcionalidade já está disponível e permite a consulta de registros vinculados ao ID único do paciente.


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
- **Request Body (exemplo):**
```json
{
  "nome": "Maria Silva",
  "cpf": "12345678912",
  "email": "maria@email.com"
}
```
#### Response Code
- 201 - Created  
- 400 - Bad Request

### Buscar paciente por ID
`GET /pacientes/{id}`

- **Descrição:** Busca paciente por ID.
- **Response Body (exemplo):**
```json
{
  "id": 1,
  "nome": "Maria Silva",
  "cpf": "12345678912",
  "email": "maria@email.com"
}
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Listar pacientes
`GET /pacientes`

- **Descrição:** Lista os pacientes cadastrados.
- **Response Body (exemplo):**
```json
[
  {
    "id": 2,
    "nome": "Maria Silva",
    "cpf": "12345678912",
    "email": "maria@email.com"
  },
  {
    "id": 1,
    "nome": "Pedro",
    "cpf": "12345681250",
    "email": "teset@gmail.com"
  }
]
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Editar paciente
`PUT /pacientes/{id}`

- **Descrição:** Edita um paciente já cadastrado.
- **Request Body (exemplo):**
```json
{
  "nome": "Maria Silva",
  "cpf": "12345678912",
  "email": "mariaSilva@email.com"
}
```
#### Response Code
- 200 - OK  
- 400 - Bad Request

### Deletar paciente
`DELETE /pacientes/{id}`
- **Descrição:** Deleta um paciente cadastrado.
#### Response Code
- 204 - No Content  
- 404 - Not Found

---

## Médicos

### Cadastrar médico
`POST /medicos`

- **Descrição:** Registra um novo médico.
- **Request Body (exemplo):**
```json
{
  "nome": "Dr. João",
  "crm": "123456",
  "email": "joao@medico.com"
}
```
#### Response Code
- 201 - Created  
- 400 - Bad Request

### Buscar médico por ID
`GET /medicos/{id}`

- **Descrição:** Busca médico por ID.
- **Response Body (exemplo):**
```json
{
  "id": 1,
  "nome": "Dr. João",
  "crm": "123456",
  "email": "joao@medico.com"
}
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Listar médicos
`GET /medicos`

- **Descrição:** Lista os médicos cadastrados.
- **Response Body (exemplo):**
```json
[
  {
    "id": 1,
    "nome": "Dr. João",
    "crm": "123456",
    "email": "joao@medico.com"
  },
  {
    "id": 2,
    "nome": "Dra. Ana",
    "crm": "654321",
    "email": "ana@medico.com"
  }
]
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Editar médico
`PUT /medicos/{id}`

- **Descrição:** Edita os dados de um médico.
- **Request Body (exemplo):**
```json
{
  "nome": "Dr. João Silva",
  "crm": "123456",
  "email": "joaosilva@medico.com"
}
```
#### Response Code
- 200 - OK  
- 400 - Bad Request

### Deletar médico
`DELETE /medicos/{id}`

- **Descrição:** Remove um médico do sistema.
#### Response Code
- 204 - No Content  
- 404 - Not Found

---

## Consultas

### Agendar consulta
`POST /consultas`

- **Descrição:** Agenda uma nova consulta entre paciente e médico.
- **Request Body (exemplo):**
```json
{
  "pacienteId": 1,
  "medicoId": 2,
  "dataHora": "2025-09-25T14:00:00"
}
```
#### Response Code
- 201 - Created  
- 400 - Bad Request
### Buscar consulta por ID
`GET /consultas/{id}`

- **Descrição:** Busca uma consulta específica.
- **Response Body (exemplo):**
```json
[
  {
    "id": 1,
    "pacienteNome": "Maria Silva",
    "medicoNome": "Dra. Ana",
    "status": "AGENDADA",
    "dataHora": "2025-09-25T16:50:34.485"
  }
]
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Listar todas as consultas
`GET /consultas`

- **Descrição:** Lista todas as consultas agendadas.
- **Response Body (exemplo):**
```json
[
  {
    "id": 1,
    "pacienteNome": "Fernanda Saito",
    "medicoNome": "Dra. Ana",
    "status": "AGENDADA",
    "dataHora": "2025-09-25T16:50:34.485"
  },
  {
      "id": 2,
      "pacienteNome": "Leonardo Camargo",
      "medicoNome": "Dr, João",
      "status": "AGENDADA",
      "dataHora": "2025-09-26T16:50:34.485"
    }
]
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Listar consultas por médico
`GET /consultas/medico/{id}`

- **Descrição:** Lista todas as consultas de um médico específico.
- **Response Body (exemplo):**
```json
[
  {
    "id": 1,
    "pacienteNome": "Maria Silva",
    "medicoNome": "Dra. Ana",
    "status": "AGENDADA",
    "dataHora": "2025-09-25T16:50:34.485"
  }
]
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Listar consultas por paciente
`GET /consultas/paciente/{id}`

- **Descrição:** Lista todas as consultas de um paciente específico.
- **Response Body (exemplo):**
```json
[
  {
    "id": 1,
    "pacienteNome": "Maria Silva",
    "medicoNome": "Dra. Ana",
    "status": "AGENDADA",
    "dataHora": "2025-09-25T16:50:34.485"
  }
]
```
#### Response Code
- 200 - OK  
- 404 - Not Found

### Editar consulta
`PUT /consultas/{id}`

- **Descrição:** Edita os dados de uma consulta.
- **Request Body (exemplo):**
```json
{
  "pacienteId": 1,
  "medicoId": 1,
  "status": "AGENDADA",
  "dataHora": "2025-09-25T16:50:34.485Z"
}
```
#### Response Code
- 200 - OK  
- 400 - Bad Request

### Cancelar consulta
`DELETE /consultas/{id}`

- **Descrição:** Cancela uma consulta agendada.
#### Response Code
- 204 - No Content  
- 404 - Not Found

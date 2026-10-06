# 🎵 Lollapalooza API

API REST em **Java + Spring Boot** com persistência em **SQL Server** (Spring Data JPA) para gerenciar os **dias** e **palcos** do festival Lollapalooza.

> Check Point 2 — Microservices and Web Engineering (2º semestre/2026)

## 🚀 Tecnologias

Java 17 · Spring Boot 4 · Spring Data JPA/Hibernate · **SQL Server** (driver `mssql-jdbc`) · Maven · Swagger/OpenAPI · Docker

## 📂 Estrutura

```text
src/main/java/br/fiap/cp1/lollapalooza
├── controller   (DiaController, PalcoController)
├── model        (Dia, Palco)  -> entidades JPA = tabelas dia e palco
├── repository   (DiaRepository, PalcoRepository) -> JpaRepository
└── LollaAPI.java
src/main/resources
├── application.properties      (perfil default - ddl-auto=update)
├── application-prd.properties  (perfil prd - ddl-auto=none)
└── migration.sql               (script de criação das tabelas no SQL Server)
```

## 🗄️ Configuração da conexão com o SQL Server

A conexão é configurada por **variáveis de ambiente** (em `application.properties`):

| Variável | Descrição | Padrão |
| :--- | :--- | :--- |
| `DB_SERVER_URL` | Host/IP do SQL Server | `localhost` |
| `DB_SERVER_PORT` | Porta | `1433` |
| `DB_SCHEMA` | Nome do banco de dados | `lollapalooza` |
| `DB_USER` | Usuário | `sa` |
| `DB_PWD` | Senha | *(vazio)* |

URL JDBC gerada:
`jdbc:sqlserver://<DB_SERVER_URL>:<DB_SERVER_PORT>;databaseName=<DB_SCHEMA>;encrypt=true;trustServerCertificate=true`

### Usando o banco disponibilizado pelo professor
Defina as variáveis com os dados fornecidos (servidor, porta, banco, usuário e senha) **antes** de executar.

**Linux/macOS**
```bash
export DB_SERVER_URL=<servidor>
export DB_SERVER_PORT=1433
export DB_SCHEMA=<banco>
export DB_USER=<usuario>
export DB_PWD=<senha>
```
**Windows (PowerShell)**
```powershell
$env:DB_SERVER_URL="<servidor>"; $env:DB_SERVER_PORT="1433"
$env:DB_SCHEMA="<banco>"; $env:DB_USER="<usuario>"; $env:DB_PWD="<senha>"
```

### Usando um SQL Server local (Docker)
```bash
docker compose up -d      # sobe SQL Server 2022 em localhost:1433 (sa / Fiap@2026Lolla)
```
Crie o banco (uma única vez) executando `src/main/resources/migration.sql` no SSMS/Azure Data Studio, ou:
```bash
docker exec -it sqlserver-lolla /opt/mssql-tools18/bin/sqlcmd -C -S localhost -U sa -P "Fiap@2026Lolla" -Q "CREATE DATABASE lollapalooza"
```
E execute a aplicação com `DB_PWD=Fiap@2026Lolla`.

> No perfil **default**, o Hibernate cria/atualiza as **tabelas** automaticamente (`ddl-auto=update`), mas o **banco** precisa existir.
> No perfil **prd** (`ddl-auto=none`), execute o `migration.sql` antes.

## ▶️ Como executar

Pré-requisitos: **Java 17+** e **Maven 3.9+**.

```bash
mvn spring-boot:run
```
A API sobe em `http://localhost:8080`.

### Via Docker
```bash
docker build -t lollapalooza-api .
docker run -d --name lollapalooza-api -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prd \
  -e DB_SERVER_URL=host.docker.internal -e DB_SERVER_PORT=1433 \
  -e DB_SCHEMA=lollapalooza -e DB_USER=sa -e DB_PWD=<senha> \
  lollapalooza-api
```

### Testes automatizados
```bash
mvn test      # usa H2 em memória, não precisa de SQL Server
```

## 🔗 Endpoints

Swagger UI: **http://localhost:8080/swagger-ui.html** · OpenAPI: `http://localhost:8080/v3/api-docs`

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| GET | `/dia` | Lista todos os dias |
| GET | `/dia/{id}` | Busca dia por ID |
| POST | `/dia` | Cria dia |
| PUT | `/dia/{id}` | Atualiza dia |
| DELETE | `/dia/{id}` | Remove dia |
| GET | `/palco` | Lista todos os palcos |
| GET | `/palco/{id}` | Busca palco por ID |
| POST | `/palco` | Cria palco |
| PUT | `/palco/{id}` | Atualiza palco |
| DELETE | `/palco/{id}` | Remove palco |

### Exemplos (curl)

```bash
# Criar um palco (grava no SQL Server)
curl -X POST http://localhost:8080/palco -H "Content-Type: application/json" -d '{
  "nome": "Palco Budweiser", "headliner": "Artista X", "capacidade": 60000,
  "localizacao": "Interlagos", "generoMusical": "Pop/Rock" }'

# Listar palcos (consulta no SQL Server)
curl http://localhost:8080/palco

# Criar um dia
curl -X POST http://localhost:8080/dia -H "Content-Type: application/json" -d '{
  "diaSemana": "Sexta-feira", "data": "2026-03-20", "climaPrevisto": "Nublado",
  "horarioAbertura": "11:00:00", "horarioFechamento": "23:00:00" }'

# Atualizar / remover
curl -X PUT http://localhost:8080/palco/1 -H "Content-Type: application/json" -d '{ "nome": "Palco Novo", "headliner": "Y", "capacidade": 40000, "localizacao": "Interlagos", "generoMusical": "Rock" }'
curl -X DELETE http://localhost:8080/palco/1
```

## 👥 Autores

* **Luana Metta Ribeiro Fernandes** — RM: 558314
* **Luísa Souza Santos** — RM: 557799

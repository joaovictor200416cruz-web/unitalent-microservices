# UniTalent — Projeto-base da Entrega 2

Arquitetura de microsserviços em Spring Boot para o TCC UniTalent.

## Stack
- Java 17
- Spring Boot 3.5.16
- Spring Cloud 2025.0.3
- Spring Cloud Gateway
- Spring Data JPA
- MySQL 8.4
- JWT
- Maven
- Docker Compose
- Postman

## Portas

| Componente | Porta |
|---|---:|
| API Gateway | 8080 |
| Auth | 8081 |
| Estudantes | 8082 |
| Empresas | 8083 |
| Vagas | 8084 |
| Candidaturas | 8085 |
| Notificações | 8086 |
| MySQL | 3306 |

## Estrutura das camadas

Cada microsserviço usa:
- Model: entidade JPA e tabela
- Repository: acesso ao banco
- Service: regras de negócio
- Controller: endpoints HTTP
- DTO: contrato de entrada/saída

## Comunicação síncrona

O microsserviço `ms-candidaturas` chama, em tempo real:
1. `ms-vagas` para validar a vaga.
2. `ms-estudantes` para validar o estudante.
3. `ms-notificacoes` para criar uma notificação.

Fluxo:

Postman -> API Gateway -> ms-candidaturas
                    -> HTTP síncrono -> ms-vagas
                    -> HTTP síncrono -> ms-estudantes
                    -> HTTP síncrono -> ms-notificacoes

## Como executar no Windows

### 1. Pré-requisitos

Instale:
- JDK 17 ou superior
- Maven 3.6.3+
- Docker Desktop
- Postman

Verifique:

```powershell
java -version
mvn -version
docker --version
docker compose version
```

### 2. Subir o MySQL

Na pasta raiz:

```powershell
docker compose up -d
```

Confira:

```powershell
docker ps
```

### 3. Compilar tudo

```powershell
mvn clean package -DskipTests
```

### 4. Abrir 7 terminais PowerShell

Terminal 1:
```powershell
java -jar api-gateway/target/api-gateway-1.0.0.jar
```

Terminal 2:
```powershell
java -jar ms-auth/target/auth-service-1.0.0.jar
```

Terminal 3:
```powershell
java -jar ms-estudantes/target/estudantes-service-1.0.0.jar
```

Terminal 4:
```powershell
java -jar ms-empresas/target/empresas-service-1.0.0.jar
```

Terminal 5:
```powershell
java -jar ms-vagas/target/vagas-service-1.0.0.jar
```

Terminal 6:
```powershell
java -jar ms-candidaturas/target/candidaturas-service-1.0.0.jar
```

Terminal 7:
```powershell
java -jar ms-notificacoes/target/notificacoes-service-1.0.0.jar
```

## Ordem para demonstrar no Postman

1. Registrar usuário
2. Fazer login e copiar o JWT
3. Criar estudante
4. Criar empresa
5. Criar vaga com `status=ATIVA`
6. Criar candidatura

Na candidatura, o `ms-candidaturas` consulta os outros microsserviços por HTTP síncrono antes de persistir.

## URLs

Gateway:
- GET http://localhost:8080/

Auth:
- POST http://localhost:8080/api/auth/register
- POST http://localhost:8080/api/auth/login

Estudantes:
- GET/POST http://localhost:8080/api/estudantes
- GET/PUT/DELETE http://localhost:8080/api/estudantes/{id}

Empresas:
- GET/POST http://localhost:8080/api/empresas
- GET/PUT/DELETE http://localhost:8080/api/empresas/{id}

Vagas:
- GET/POST http://localhost:8080/api/vagas
- GET/PUT/DELETE http://localhost:8080/api/vagas/{id}

Candidaturas:
- GET/POST http://localhost:8080/api/candidaturas
- GET http://localhost:8080/api/candidaturas/{id}

Notificações:
- GET/POST http://localhost:8080/api/notificacoes
- GET/PUT/DELETE http://localhost:8080/api/notificacoes/{id}

## Observação para a apresentação

Este projeto-base prioriza exatamente a Entrega 2: separação Model/Repository/Service/Controller/DTO, API Gateway, persistência isolada e comunicação síncrona entre microsserviços. O rate limiting do Gateway é propositalmente simples, em memória, para fins acadêmicos; para produção, deve ser substituído por uma solução distribuída.

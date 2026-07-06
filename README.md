# SplashHelp API

API REST para um sistema de aluguel de carros. O projeto permite cadastrar usuarios, cargos, categorias, carros, documentos, locacoes, pagamentos e avaliacoes. A autenticacao e feita com JWT e as rotas protegidas usam permissoes por cargo.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Bean Validation
- Swagger/OpenAPI
- Maven

## Funcionalidades

- Cadastro e login de usuarios
- Autenticacao com token JWT
- Controle de acesso por cargo
- CRUD de usuarios
- CRUD de cargos
- CRUD de categorias de carros
- CRUD de carros com upload de imagem
- Cadastro e validacao de documentos
- Criacao e gestao de locacoes
- Confirmacao de retirada, devolucao e cancelamento de locacoes
- Geracao e confirmacao de pagamentos
- Cadastro e listagem de avaliacoes

## Como Rodar

### 1. Requisitos

Tenha instalado:

- Java 17 ou superior
- MySQL
- Maven ou o wrapper do projeto

### 2. Banco de dados

O projeto esta configurado para usar MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/splashhelp?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12345678
```

Se o usuario ou senha do seu MySQL forem diferentes, altere o arquivo:

```text
src/main/resources/application.properties
```

### 3. Token JWT

O segredo do JWT pode ser configurado pela variavel de ambiente:

```text
JWT_SECRET
```

Se nao for definido, o projeto usa o valor padrao:

```text
my-secret-key
```

### 4. Executar a API

No Windows:

```bash
./mvnw.cmd spring-boot:run
```

Ou, se estiver usando Maven instalado:

```bash
mvn spring-boot:run
```

A API ficara disponivel em:

```text
http://localhost:8080
```

## Documentacao Swagger

Depois de iniciar o projeto, acesse:

```text
http://localhost:8080/swagger-ui.html
```

Tambem pode acessar:

```text
http://localhost:8080/swagger-ui/index.html
```

## Autenticacao

### Cadastro

```http
POST /api/auth/cadastrar
```

Exemplo de corpo:

```json
{
  "usuarioRequest": {
    "nome": "Joao Silva",
    "email": "joao@email.com",
    "senha": "123456",
    "telefone": "(11) 99999-9999",
    "cargo": {
      "nomeDoCargo": "User_Client"
    },
    "statusUsuario": "ATIVO"
  }
}
```

### Login

```http
POST /api/auth/login
```

Exemplo de corpo:

```json
{
  "email": "joao@email.com",
  "senha": "123456"
}
```

Exemplo de resposta:

```json
{
  "token": "jwt_token_aqui"
}
```

Para acessar rotas protegidas, envie o token no header:

```http
Authorization: Bearer jwt_token_aqui
```

## Permissoes

Rotas publicas:

- `POST /api/auth/cadastrar`
- `POST /api/auth/login`
- `/swagger-ui/**`
- `/v3/api-docs/**`

Cargos usados nas regras de seguranca:

- `ADMIN`
- `User_Client`
- `USER_Prop`

Observacao: qualquer rota que nao tenha permissao especifica exige o cargo `ADMIN`.

## Endpoints Principais

### Usuarios

```http
GET /api/usuario
GET /api/usuario/{id}
POST /api/usuario
PUT /api/usuario/{id}
DELETE /api/usuario/{id}
```

### Cargos

```http
GET /api/cargo
GET /api/cargo/{id}
POST /api/cargo
PUT /api/cargo/{id}
DELETE /api/cargo/{id}
```

### Categorias

```http
GET /api/Categoria
GET /api/Categoria/{id}
POST /api/Categoria
PUT /api/Categoria/{id}
DELETE /api/Categoria/{id}
```

### Carros

```http
GET /api/carro
GET /api/carro/{id}
POST /api/carro
PUT /api/carro/{id}
DELETE /api/carro/{id}
```

O cadastro de carro usa `multipart/form-data`, pois tambem recebe uma imagem.

Campos esperados:

```text
request: JSON com os dados do carro
imagem: arquivo da imagem
```

Exemplo do campo `request`:

```json
{
  "categoria": {
    "id": "uuid-da-categoria"
  },
  "marca": "Toyota",
  "cor": "Preto",
  "modelo": "Corolla",
  "ano": 2022,
  "placa": "ABC-1234",
  "preco": 15000,
  "statusCarro": "LIVRE"
}
```

### Documentacao

```http
GET /api/Documentacao
GET /api/Documentacao/{id}
POST /api/Documentacao
PUT /api/Documentacao/{id}
DELETE /api/Documentacao/{id}
```

### Locacoes

```http
GET /api/locacao
GET /api/locacao/{id}
POST /api/locacao
PUT /api/locacao/{id}
PUT /api/locacao/{id}/retirar
GET /api/locacao/retirar/lista
PUT /api/locacao/{id}/devolver
PUT /api/locacao/{id}/cancelar
GET /api/locacao/cancelar/listar
DELETE /api/locacao/{id}
```

### Pagamentos

```http
GET /api/pagamento
POST /api/pagamento/gerarpagamento
PUT /api/pagamento/{id}/confirmar
```

### Avaliacoes

```http
GET /api/avaliacao
GET /api/avaliacao/{id}
POST /api/avaliacao
DELETE /api/avaliacao/{id}
```

## Enums

### StatusUsuario

```text
ATIVO
BLOQUEADO
```

### StatusCarro

```text
LIVRE
ALUGADO
MANUTECAO
REJEITADO
```

### StatusLocacao

```text
PENDENTE
CONFIRMADO
ATIVO
CONCLUIDO
CANCELADO
```

### StatusPagamento

```text
PENDENTE
PAGO
REJEITADO
FALHOU
```

### MetodoPagamento

```text
PIX
DEBITO
CREDITO
```

### StatusDoc

```text
PENDENTE
VALIDO
REJEITADO
```

## Upload de Arquivos

O projeto aceita arquivos de ate 10MB:

```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

As imagens dos carros sao salvas localmente na pasta configurada pelo service.

## Estrutura do Projeto

```text
src/main/java/com/CSplashAluguel
├── Config
├── Controller
├── DTO
│   ├── Request
│   └── Response
├── Exceptions
├── Model
├── Repository
├── Security
└── Service
```

## Observacoes

- O projeto usa `spring.jpa.hibernate.ddl-auto=update`, entao as tabelas sao atualizadas automaticamente pelo Hibernate.
- O endpoint de pagamento no `SecurityConfig` possui uma regra escrita como `/api/pagmento/{id}/confirmar`, mas o controller usa `/api/pagamento/{id}/confirmar`.
- Para apresentar o projeto, recomenda-se criar dados iniciais de cargo, usuario administrador, categoria e carros.

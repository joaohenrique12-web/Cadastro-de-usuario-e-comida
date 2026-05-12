API Calorias

API REST desenvolvida com Spring Boot para gerenciamento de usuários e alimentos, permitindo cadastro, consulta, atualização e remoção de dados relacionados ao controle de calorias.

Funcionalidades
Usuários
Cadastrar usuário
Listar usuários
Buscar usuário por ID
Atualizar usuário
Excluir usuário
Alimentos
Cadastrar alimento
Listar alimentos com paginação
Buscar alimento por ID
Buscar alimento por nome
Buscar alimentos por faixa de calorias
Atualizar alimento
Excluir alimento
Tecnologias Utilizadas
Java 17+
Spring Boot
Spring Web
Spring Data JPA
Bean Validation
Oracle Database
Flyway
Maven
Estrutura do Projeto
src
├── controller
├── dto
├── model
├── repository
├── service
├── advice
└── config

Base URL
http://localhost:8080/api
Endpoints de Usuários
Cadastrar usuário
POST /usuarios
Exemplo de Request
{
"nome": "João Henrique",
"email": "joao@email.com",
"senha": "123456"
}
Resposta
{
"id": 1,
"nome": "João Henrique",
"email": "joao@email.com"
}
Listar usuários
GET /usuarios
Buscar usuário por ID
GET /usuarios/{id}
Exemplo
GET /usuarios/1
Atualizar usuário
PUT /usuarios
Exemplo de Request
{
"nome": "João Atualizado",
"email": "novoemail@email.com"
}
Excluir usuário
DELETE /usuarios/{usuarioId}
Endpoints de Alimentos
Cadastrar alimento
POST /alimentos
Exemplo de Request
{
"nome": "Banana",
"quantidadeCalorias": 89
}
Listar alimentos (Paginado)
GET /alimentos
Query Params
Parâmetro	Tipo	Descrição
page	Integer	Página atual
size	Integer	Quantidade por página
Exemplo
GET /alimentos?page=0&size=2
Buscar alimento por ID
GET /alimentos/{alimentoId}
Buscar alimento por nome
GET /alimentos?nome=Banana
Buscar alimentos por faixa de calorias
GET /alimentos?caloriasMinima=50&caloriasMaxima=200
Atualizar alimento
PUT /alimentos
Exemplo de Request
{
"id": 1,
"nome": "Banana Prata",
"quantidadeCalorias": 95
}
Excluir alimento
DELETE /alimentos/{alimentoId}
Configuração do Projeto
application.properties

Exemplo seguro:

spring.application.name=calorias

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.baseline-on-migrate=true

spring.web.error.include-stacktrace=never

minha.palavra.secreta=${JWT_SECRET}
Como Executar
Clone o repositório
git clone SEU_REPOSITORIO
Acesse a pasta
cd calorias
Execute a aplicação

Windows:

mvnw spring-boot:run

Linux/Mac:

./mvnw spring-boot:run
Tratamento de Erros

A API utiliza:

@Valid
Bean Validation
ResponseEntity
Tratamento global de exceções
Banco de Dados

Banco utilizado:

Oracle Database

Ferramenta de migração:

Flyway
Autor

Desenvolvido por João Henrique com Spring Boot.

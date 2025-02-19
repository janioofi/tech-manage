# TechManage

## Descrição
TechManage é um sistema de gerenciamento de usuários construído utilizando Spring Boot. Ele permite criar, atualizar, buscar e deletar informações de usuários.

## Requisitos

- JDK 21
- Maven 3.6+

## Instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/janioofi/tech-manage.git
    cd tech-manage
    ```

2. Construa o projeto utilizando Maven:
    ```bash
    mvn clean install
    ```

3. Execute o projeto:
    ```bash
    mvn spring-boot:run
    ```

## Uso

### Endpoints da API

- **GET /api/users**: Retorna todos os usuários.
    ```bash
    curl -X GET "http://localhost:8080/api/users" -H "accept: application/json"
    ```

- **GET /api/users/{id}**: Retorna um usuário pelo ID.
    ```bash
    curl -X GET "http://localhost:8080/api/users/1" -H "accept: application/json"
    ```

- **POST /api/users**: Cria um novo usuário.
    ```bash
    curl -X POST "http://localhost:8080/api/users" -H "accept: application/json" -H "Content-Type: application/json" -d '{
      "fullName": "Janio Filho",
      "email": "janio@gmail.com",
      "phone": "+55 11 91003-9540",
      "birthDate": "1990-01-01",
      "userType": "EDITOR"
    }'
    ```

- **PUT /api/users/{id}**: Atualiza um usuário existente.
    ```bash
    curl -X PUT "http://localhost:8080/api/users/1" -H "accept: application/json" -H "Content-Type: application/json" -d '{
      "fullName": "Janio Filho Updated",
      "email": "janio_updated@gmail.com",
      "phone": "+55 11 91003-9540",
      "birthDate": "1990-01-01",
      "userType": "EDITOR"
    }'
    ```

- **DELETE /api/users/{id}**: Deleta um usuário pelo ID.
    ```bash
    curl -X DELETE "http://localhost:8080/api/users/1" -H "accept: application/json"
    ```

## Documentação

### Swagger
A documentação da API pode ser acessada através do Swagger:
[Swagger UI](http://localhost:8080/swagger-ui/index.html)

### Console do H2
O console do banco de dados H2 pode ser acessado em:
[H2 Console](http://localhost:8080/h2-console)

#### Informações de Acesso ao Banco

- **Saved Settings**: Generic H2 (Embedded)
- **Setting Name**: Generic H2 (Embedded)
- **Driver Class**: org.h2.Driver
- **JDBC URL**: jdbc:h2:mem:testdb
- **User Name**: sa
- **Password**: (deixe em branco)

## Testes

### Testes Unitários

Para rodar os testes unitários, execute:
```bash
mvn test
```

### Testes de Integração

Para rodar os testes de integração, execute:
```bash
mvn verify
```

## Autores

- [Janio Filho](https://github.com/janioofi)

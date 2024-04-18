# Restaurante-api
Expoe endpoints para gerenciamento de restaurante

### Tecnologias e Ferramentas

- Java 17
- Gradle
- H2
- Docker
- Junit, Mockito
- Swagger

### Subir aplicacao

- build: docker build -t restaurante-api .
- execute: docker run -p 8080:8080 restaurante-api

Com aplicacao rodando acesse os endpoints: http://localhost:8080/swagger-ui/index.html 

Acesso ao console do H2 : http://localhost:8080/h2-console


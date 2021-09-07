#Projeto desafio Sensedia
 - Acesso a documentacao da API
 >host/swagger-ui/index.html
 
 - Executar app com docker
> 1 - docker build -t sensedia/joker
> 2 - docker run -p 8080:8080 sensedia/joker

### Endpoints
- Get
> /v1/joke
> /v1/joke/average
> /v1/joke/unrateds

- Post
> /v1/joke/rate

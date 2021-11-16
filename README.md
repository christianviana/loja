# Projeto Loja Quero-Quero

Este projeto usa o framework Java Quarkus: https://quarkus.io/

## Pré-requisitos

- JDK 11+ instalado com JAVA_HOME configurado
- Apache Maven 3.8.1+
- Docker: https://www.docker.com/


## Pré-instalação: instalando o servidor de banco de dados

Para instalar uma imagem docker executando um servidor de banco de dados Postgresql, execute o comando abaixo:

 `docker run -p 5432:5432 --name quero-quero-db -e POSTGRES_PASSWORD=segr123! -d postgres`
 
> **Nota**: Para executar o docker sem privilégio de root, siga essas [instruções](https://docs.docker.com/engine/install/linux-postinstall/#manage-docker-as-a-non-root-user).
 
## Instalação (opção 1): via fontes no GitHub

1. Clonar o [repositório](https://github.com/christianviana/loja) em sua máquina local.

2. Dentro do diretório raiz do repositório, executar o comando:

  `./mvnw compile quarkus:dev`

Este comando executa a aplicação no modo desenvolvimento (dev mode).

## Instalação (opção 2): via imagem Docker

Foi configurada no projeto a extensão quarkus-container-image-docker para geração automática da imagem docker. 
Para instalar e executar a aplicação, siga os passos abaixo:

1. Criar a imagem:

  `./mvnw package`

2. Executar a imagem:

  `docker run -i --rm -p 8080:8080 loja-quero-quero`

> **Nota**: Para que o contâiner da aplicação consiga acessar a porta do banco de dados no contêiner do banco, é preciso criar uma rede e conectar os dois contâineres. Para realizar essa operação, siga essas [intruções](https://stackoverflow.com/questions/42385977/accessing-a-docker-container-from-another-container).


## Documentação da API

- A documentação da API foi gerada no padrão Open API com o auxílio da extensão Quarkus smallrye-openapi e
pode ser acessa na url: http://localhost:8080/q/swagger-ui/

## Alguns exemplos de utilização da API:

- Criação de Produto:

```
 curl -X 'POST' \
  'http://localhost:8080/api/v1/produtos' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{  
  "nome": "produto A",
  "preco": 10.25
}' -w "\n"
```

- Busca de Vendedor por Matrícula:

```
curl -X 'GET' \
  'http://localhost:8080/api/v1/vendedores/1' \
  -H 'accept: */*' -w "\n"
```

- Busca a lista dos vendedores por maior valor vendido:

```
curl -X 'GET' \
  'http://localhost:8080/api/v1/vendedores/maiores-por-valor' \
  -H 'accept: */*' -w "\n"
```


## Arquitetura para alta carga nos endpoints de estatística

Algumas mudanças que podem ser feitas para que os endpoints de estatística suportem uma carga maior:

1 - Uso de caching de queries do Hibernate: https://quarkus.io/guides/hibernate-orm#caching-of-queries

2 - Criar uma aplicação para a parte CRUD e outra separada para a parte de estatística, seguindo a seguinte arquitetura:

 - Empacotar as camas de negócio, serviço e DAO em um jar, para que o modelo e regras de negócio possam ser reutilizados
  tanto na aplicação de CRUD quanto na de estatísticas
  - Criar duas aplicações separadas, uma para CRUD e outra para estatísticas, as duas tem como dependência o JAR do negócio
  - A aplicação CRUD depende do JAR de negócio e acrescrenta os endpoints do CRUD
  - A aplicação de estatística também depende do JAR de negócio, mas com apenas os endpoints de estatística
  - A aplicação de estatística pode fazer de bens do tipo ApplicationScoped, pois são apenas de pesquisa e não terão problemas de concorrência. Com esses escopo, espera-se menor footprint de memória de beans, e menor tempo de criação de request, pois não precisa criar e injetar o bean a cada usuário diferente  
  - Usar [Kubernetes](https://kubernetes.io/) para criar uma arquitetura elástica, que escale horizontalmente automaticamente quando a carga aumentar
  
  - Uma esboço dessa solução pode ser visto [aqui](criar diagrama)


## Histórico de escolhas arquiteturais do projeto

1. Missão: achar um servidor web light-weighted, já que não podia usar Spring Boot

- Micronaut parecia uma boa opção
- Achei documento falando que Quarkus tem performance melhor: https://simply-how.com/quarkus-vs-micronaut
- Micronaut com muitas opções complicadas na geração
- Quarkus mais simples e com opção de geração básica com o que eu preciso 
- E documentação mais fácil e farta, vários tutoriais simples e completos
- Já vem preparado pra docker
- Great features: security, validation, caching
- Decidido ir com o quarkus

2. Montar estrutura do projeto: maven com jersey, junit, etc 

- Montei com Quarkus, Jax-rs, DI, Junit, Mockito, Logs, Jackson, Swagger
- Instalei Agroal (pool de conexões), Hibernate ORM e Postgres JDBC
- Ia usar H2 para desenvolvimento, mas resolvi usar postgresql em contâiner docker

3. Decidido que será um único microserviço pra escalar horizontalmente, com banco único acessado pelas instâncias dos microservicos 

4. Por precaução quanto à concorrência, resolvi colocar http-session, precisaria mais testes pra avaliar

5. Não coloquei https, não era requisito

6. Testes

- Resolvi não escrever testes unitários das classes bo e dao, classes muito simples
- Escrevi testes unitários das classes service. Acabaram ficando muito simples também, mas fiz alguns
- Escrevi alguns testes de integração com rest assured 

## Melhorias a serem feitas

- Construir DTO's para fazer mapeamento das entidades dos serviços REST. Usar a própria entidade de negócio mapeada pelo JPA está ocasionando ter que fazer alguns "malabarismos" para evitar conflitos entre um mapeamento e o outro.
- Os serviços foram implementados com escopo de sessão, usando o  quarkus-undertow. Estudar um pouco mais os efeitos do ApplicationScoped para verificar se ele seria seguro em um ambiente de alta concorrência. E realizar testes com alta carga.
- Testes unitários: aumentar cobertura
- Testes de integração: fiz apenas alguns para ver como funciona, fazer mais
- Testes de integração: dependem dos dados de carga do banco, verificar se não há maneira melhor de fazer

## Pendências

- alguns testes unitários (service)

- alguns testes de integração

- fazer diagrama colocar banco no diagrama?
- melhorar arquivo de carga inicial do banco para testes

- testar image docker (resolver questão da rede)
- revisar este readme na web (enviar link no e-mail de entrega)
- revisão nas classes (comentários e etc)


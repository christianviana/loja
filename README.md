# Projeto Loja Quero-Quero

Este projeto usa o framework Java Quarkus: https://quarkus.io/

## Pré-requisitos

- JDK 11+ instalado com JAVA_HOME configurado
- Apache Maven 3.8.1+
- Docker: https://www.docker.com/


## Pré-instalação: instalando o servidor de banco de dados

Para instalar uma imagem docker executando um servidor de banco de dados Postgresql, execute o comando abaixo:

> `docker run -p 5432:5432 --name quero-quero-db -e POSTGRES_PASSWORD=segr123! -d postgres`
 
> **Nota**: Para executar o docker sem privilégio de root, siga essas [instruções](https://docs.docker.com/engine/install/linux-postinstall/#manage-docker-as-a-non-root-user).
 
## Instalação (opção 1): via fontes no GitHub

1. Clonar o [repositório](https://github.com/christianviana/loja) em sua máquina local.

2. Dentro do diretório raiz do repositório, executar o comando:

> `./mvnw compile quarkus:dev`

> Este comando executa a aplicação no modo desenvolvimento (dev mode).

## Instalação (opção 2): via imagem Docker

Foi configurada no projeto a extensão quarkus-container-image-docker para geração automática da imagem docker. 
Para instalar e executar a aplicação, siga os passos abaixo:

1. Criar a imagem:

> `./mvnw package`

2. Executar a imagem:

> `docker run -i --rm -p 8080:8080 loja-quero-quero`

> **Nota**: Para que o contâiner da aplicação consiga acessar a porta do banco de dados no contêiner do banco, é preciso criar uma rede e conectar os dois contâineres. Para realizar essa operação, siga essas [intruções](https://stackoverflow.com/questions/42385977/accessing-a-docker-container-from-another-container).

## Documentação da API


- A documentação da API foi gerada no padrão Open API com o auxílio da extensão Quarkus smallrye-openapi e
pode ser acessa na url: 

> `http://localhost:8080/q/swagger-ui/`

## Alguns exemplos de utilização da API:

- Criação de Produto:

```
 curl -X 'POST' \
  'http://localhost:8080/api/v1/produtos' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "codigo": "P1",
  "nome": "produto A",
  "preco": 10.25
}' -w "\n"
```

- Busca de Vendedor por Matrícula:

```
curl -X 'GET' \
  'http://localhost:8080/api/v1/vendedores/MM' \
  -H 'accept: */*' -w "\n"
```

- Busca a lista dos vendedores por maior valor vendido:

```
curl -X 'GET' \
  'http://localhost:8080/api/v1/vendedores/maiores-por-valor' \
  -H 'accept: */*' -w "\n"
```

## Arquitetura para alta carga nos endpoints de estatística

https://quarkus.io/guides/cdi-reference#container-managed-concurrency
https://stackoverflow.com/questions/60071059/how-is-it-safe-to-inject-entitymanager-in-applicationscoped-bean-in-quarkus

- caching do hibernate ORM do quarkus: https://quarkus.io/guides/hibernate-orm#caching-of-queries
- jar com parte de negócio pra baixo, pra poder reutilizar modelo, serviço, dao, etc tanto no crud quanto nas estatísticas
- microserviços separados com endpoints de estatística
- escalar horizontalmente com beans do tipo applicationScoped - menor footprint de memória de menor tempo de criação de request, pois não precisa criar e injetar o bean
- uasr k8s

- criar diagrama



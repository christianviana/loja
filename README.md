# Projeto Loja Quero-Quero

Este projeto usa o framework Java Quarkus: https://quarkus.io/

## Pré-requisitos

- JDK 11+ instalado com JAVA_HOME configurado
- Apache Maven 3.8.1+
- Docker: https://www.docker.com/


## Pré-instalação: instalando o servidor de banco de dados

Para instalar uma imagem docker executando um servidor de banco de dados Postgresql, execute o comando abaixo:

FALTA REFERENCIAR A IMAGEM!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
PRECISA CADASTRAR ALGUM REPOSITORIO:??????
MELHOR EU FORNECER A IMAGEM 
1. Baixar a imagem 

2. Executar a imagem

> `docker run -p 5432:5432 --name quero-quero-db -e POSTGRES_PASSWORD=segr123! -d postgres`
 
> **Nota**: Para executar o docker sem privilégio de root, siga essas [instruções](https://docs.docker.com/engine/install/linux-postinstall/#manage-docker-as-a-non-root-user).
 
## Instalação (opção 1): via fontes no GitHub

1. Clonar o [repositório](https://github.com/christianviana/loja) em sua máquina local.

2. Dentro do diretório raiz do repositório, executar o comando:

> `./mvnw compile quarkus:dev`

> Este comando executa a aplicação no modo desenvolvimento (dev mode).

## Instalação (opção 2): via imagem Docker

1. Baixar a imagem 

2. Executar a imagem


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


https://quarkus.io/guides/deploying-to-kubernetes
https://quarkus.io/guides/cdi-reference#container-managed-concurrency
https://stackoverflow.com/questions/60071059/how-is-it-safe-to-inject-entitymanager-in-applicationscoped-bean-in-quarkus

- k8s
- criar os serviços separadamente, dividindo por área de negócio?
ou um único?
- para os endpoints de estatística poderem receber carga maior, qual a melhor solução?
- jar com parte de negócio pra baixo, pra poder reutilizar modelo, serviço, dao, etc tanto no crud quanto nas estatísticas
- microserviços separados com endpoints de estatística
- usar caching do banco
- ver opções de caching que fala na parte de hibernate ORM do quarkus
- https://quarkus.io/guides/hibernate-orm#caching-of-queries
- escalar verticalmente com beans do tipo applicationScoped - menor footprint de memória de menor tempo de criação de request, pois não precisa criar e injetar o bean



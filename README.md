# Monitoramento Ambiental

Este é um projeto de monitoramento ambiental que utiliza uma API desenvolvida em Java com Spring Boot. A aplicação é projetada para coletar dados sobre qualidade do ar e gerenciar alertas de desastres naturais.

## Índice

- [Requisitos](#requisitos)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Instalação e Configuração](#instalação-e-configuração)
- [Como Iniciar a Aplicação](#como-iniciar-a-aplicação)
- [Endpoints da API](#endpoints-da-api)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Requisitos

- Java 21 ou superior
- Maven 3.8 ou superior
- Docker (opcional, para uso em contêineres)
- Docker Compose (opcional, para orquestração de contêineres)

## Tecnologias Utilizadas

- **Spring Boot**: Para desenvolvimento da API REST.
- **Oracle**: Banco de dados NoSQL utilizado para armazenamento de dados.
- **Docker**: Para criação e gestão de contêineres.
- **GitHub Actions**: Para CI/CD (integração e entrega contínua).
- **Azure Web App**: Para rodar a aplicação.
## Estrutura do Projeto

```plaintext
monitoramento-ambiental/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── Dockerfile
├── docker-compose.yml
└── README.md
```

## Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone https://github.com/CiriloDesu/monitoramentoAmbiental.git
cd monitoramentoAmbiental
```

### 2. Configurar Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```plaintext
DATABASE_URL=jdbc:oracle:thin:URL_BD_AQUI:1521:ORCL
DATABASE_USER=USER
DATABASE_PWD=PASSWORD
AZURE_PROFILE=your_azure_publish_profile
DOCKERHUB_USERNAME=your_dockerhub_username
DOCKERHUB_TOKEN=your_dockerhub_token
```

### 3. Configurar o Banco de Dados

Caso utilize Docker, você pode iniciar um contêiner com o ORACLE utilizando o `docker-compose.yml` incluído no projeto:

```bash
docker-compose up -d
```

## Como Iniciar a Aplicação

### 1. Com Maven

Para iniciar a aplicação localmente, você pode usar o Maven. Execute os seguintes comandos:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### 2. Com Docker

Caso você tenha configurado o Docker, você pode construir a imagem e iniciar o contêiner:

```bash
docker build -t monitoramentoambiental .
docker run -p 8080:8080 --env-file .env monitoramentoambiental
```

## Endpoints da API

Abaixo estão alguns dos principais endpoints disponíveis na API:

### Alertas

- `POST /alertas` - Criar um novo alerta.
- `GET /alertas` - Listar todos os alertas.
- `DELETE /alertas/{id}` - Remover um alerta específico.

### Qualidade do Ar

- `POST /qualidade-do-ar` - Adicionar dados de qualidade do ar.
- `GET /qualidade-do-ar` - Listar dados de qualidade do ar.

## Contribuição



## Licença

Sei lá, só pra facul.

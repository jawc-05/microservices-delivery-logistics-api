[![en](https://img.shields.io/badge/lang-en-blue)](README.md)

# Sistema de Gestão de Entregas

Arquitetura de microsserviços para gestão de pedidos, entregas, autenticação e notificações, desenvolvida em Java com Spring Boot.

Este projeto simula um ecossistema real de logística e distribuição, com serviços independentes, comunicação síncrona via OpenFeign, autenticação com JWT, persistência poliglota e infraestrutura automatizada com Docker Compose.

A proposta principal é demonstrar boas práticas de engenharia de software, incluindo arquitetura orientada a serviços, separação de responsabilidades, escalabilidade, automação local e uso de tecnologias adequadas para cada domínio de negócio.

## Arquitetura do sistema

A aplicação é composta por quatro microsserviços, cada um com responsabilidade específica e comunicação direta com os demais:

![Arquitetura do sistema](delivery-logistics-backend/docs/architecture-diagram.png)

### 1. Auth Service

Responsável por:

- Autenticação de usuários;
- Login e autorização;
- Geração e validação de tokens JWT;
- Controle de perfis, como administrador e operador.

### 2. Order Service

Responsável pela gestão dos pedidos:

- Criação de pedidos;
- Cancelamento;
- Consulta e listagem;
- Atualização de status;
- Envio de eventos e integração com entregas.

### 3. Delivery Service

Responsável pela gestão logística:

- Consulta de entregadores disponíveis;
- Atribuição de pedidos;
- Controle de entregas e status;
- Suporte à operação de entrega.

### 4. Notification Service

Responsável pelo histórico e registro de eventos:

- Criação de pedidos;
- Atualização de status;
- Notificações de entrega;
- Registro de acontecimentos do sistema.

## Fluxo principal

O fluxo de funcionamento do sistema segue a seguinte lógica:

1. O usuário faz login no `Auth Service`;
2. O sistema retorna um token JWT;
3. O cliente autenticado cria um pedido no `Order Service`;
4. O `Order Service` consulta o `Delivery Service` para localizar um entregador disponível;
5. O pedido é associado à entrega;
6. O `Notification Service` registra os eventos relevantes;
7. O status da entrega é atualizado ao longo do processo.

## Persistência poliglota

O projeto utiliza diferentes bancos de dados de acordo com o contexto de cada serviço:

| Banco de dados | Serviço | Finalidade |
|---|---|---|
| PostgreSQL | Order Service | Pedidos e dados transacionais |
| MySQL | Delivery Service | Dados relacionais da logística |
| MongoDB | Notification Service | Histórico de notificações e eventos |

Essa abordagem é chamada de persistência poliglota e permite escolher a base mais adequada para cada domínio.

## Relatórios analíticos com JDBC

Além do uso de JPA/Hibernate nas operações transacionais, o `Order Service` também utiliza JDBC puro para consultas analíticas e relatórios.

Essa estratégia é importante para evitar gargalos de performance e problemas comuns de ORM, como:

- Consultas N+1;
- Sobrecarga de mapeamento em relatórios complexos;
- Baixa flexibilidade para SQL analítico otimizado.

Entre os relatórios esperados estão:

- Pedidos por dia;
- Tempo médio de entrega;
- Entregadores com melhor desempenho;
- Indicadores operacionais.

## Infraestrutura e automação

A execução local do projeto é automatizada com Docker Compose e scripts de shell.

A infraestrutura inclui:

- Quatro microsserviços;
- PostgreSQL;
- MySQL;
- MongoDB;
- Rede isolada interna para comunicação entre os containers.

Os scripts disponíveis ajudam a iniciar, parar e resetar o ambiente de forma simples.

## Tecnologias utilizadas

- Java 21;
- Spring Boot;
- Spring Security;
- JWT;
- OpenFeign;
- Hibernate/JPA;
- JDBC;
- PostgreSQL;
- MySQL;
- MongoDB;
- Docker;
- Docker Compose;
- Shell Script;
- Swagger/OpenAPI.

## Demonstração

A demonstração apresenta o funcionamento da API e da arquitetura de microsserviços, incluindo autenticação, comunicação entre serviços, criação e atualização de pedidos, consulta de entregadores e registro de notificações.

<video
  src="https://github.com/user-attachments/assets/d4bcba4c-901e-4c67-b0fe-d8a3f552f419"
  autoplay
  muted
  playsinline
  controls
  width="100%">
</video>

## Como executar localmente

### Pré-requisitos

- Git;
- Java 21;
- Docker;
- Docker Compose;
- Bash ou shell compatível.

### Clonar o projeto

```bash
git clone https://github.com/jawc-05/microservices-delivery-logistics-api.git
cd microservices-delivery-logistics-api
```

### Dar permissão aos scripts

```bash
chmod +x start.sh stop.sh reset.sh
```

### Iniciar o ambiente

```bash
./start.sh
```

### Parar os serviços

```bash
./stop.sh
```

### Resetar o ambiente

```bash
./reset.sh
```

### Executar diretamente com Docker Compose

```bash
docker compose up --build
```

Para executar em segundo plano:

```bash
docker compose up --build -d
```

Para visualizar os containers:

```bash
docker compose ps
```

Para acompanhar os logs:

```bash
docker compose logs -f
```

## Documentação da API

As APIs são documentadas utilizando Swagger/OpenAPI.

Após iniciar os serviços, acesse a documentação no endereço correspondente de cada aplicação:

```text
http://localhost:<porta>/swagger-ui/index.html
```

As portas devem ser verificadas no arquivo `docker-compose.yml` ou nas configurações individuais dos serviços.

## Estrutura do projeto

```text
.
├── delivery-logistics-backend/
│   ├── auth-service/
│   ├── order-service/
│   ├── delivery-service/
│   ├── notification-service/
│   └── docs/
│       ├── architecture-diagram.png
│       └── demo.mp4
├── docker-compose.yml
├── start.sh
├── stop.sh
├── reset.sh
├── README.md
└── .gitignore
```

## Possíveis evoluções

- API Gateway;
- Service Discovery;
- Mensageria com RabbitMQ ou Kafka;
- Circuit Breaker;
- Testes de integração com Testcontainers;
- Monitoramento com Prometheus e Grafana;
- Centralização de logs;
- Pipeline de CI/CD;
- Deploy em ambiente de nuvem.

## Autor

Desenvolvido por **João Alfredo Williges Cunha**.

Projeto voltado à demonstração de arquitetura de microsserviços, engenharia de software e desenvolvimento backend com Java.

## 📝 Licença

Este projeto está sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

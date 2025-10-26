# 🎥 MovieFlix

**MovieFlix** é uma aplicação RESTful construída com **Spring Boot**, projetada para gerenciar filmes, categorias e usuários de forma segura e escalável.  
O sistema utiliza autenticação JWT, versionamento de banco de dados com Flyway e segue as boas práticas de arquitetura e segurança do Spring.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Descrição |
|-------------|-----------|
| **Java 17+** | Linguagem principal |
| **Spring Boot 3** | Framework base |
| **Spring Web** | Criação de APIs REST |
| **Spring Security (JWT)** | Autenticação e autorização stateless |
| **Flyway** | Migrações e versionamento do banco de dados |
| **Spring Data JPA** | Acesso a dados com Hibernate |
| **H2 / PostgreSQL / MySQL** | Banco de dados (dependendo do ambiente) |
| **Lombok** | Redução de boilerplate no código |
| **Maven** | Gerenciamento de dependências |

---

## 🧩 Estrutura do Projeto

src/
├── main/
│ ├── java/com/movieflix/
│ │ ├── config/ → Segurança, JWT e beans do Spring
│ │ ├── controller/ → Endpoints REST
│ │ ├── entity/ → Entidades do banco de dados
│ │ ├── repository/ → Repositórios JPA
│ │ ├── service/ → Regras de negócio
│ │ └── dto/ → Objetos de transferência de dados
│ └── resources/
│ ├── db/migration/ → Scripts Flyway (ex: V1__create_tables.sql)
│ ├── application.yml → Configurações da aplicação
│ └── static/ → (opcional) Recursos estáticos
└── test/ → Testes unitários e de integração

yaml
Copy code

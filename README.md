# 🎥 MovieFlix

MovieFlix é uma aplicação RESTful robusta construída com Spring Boot, projetada para gerenciar filmes, categorias e usuários de forma segura e escalável. O sistema utiliza autenticação JWT, versionamento de banco de dados com Flyway e segue as melhores práticas de arquitetura e segurança do ecossistema Spring.

## 📋 Índice

- [🚀 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [✨ Funcionalidades](#-funcionalidades)
- [🧩 Estrutura do Projeto](#-estrutura-do-projeto)
- [📦 Pré-requisitos](#-pré-requisitos)
- [⚙️ Instalação e Configuração](#️-instalação-e-configuração)
- [🏃 Executando a Aplicação](#-executando-a-aplicação)
- [🔌 Endpoints da API](#-endpoints-da-api)
- [🔒 Segurança](#-segurança)
- [🗃️ Migrações de Banco de Dados](#️-migrações-de-banco-de-dados)

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|---------|-----------|
| Java | 17+ | Linguagem principal |
| Spring Boot | 3.x | Framework base para aplicações enterprise |
| Spring Web | 3.x | Criação de APIs REST |
| Spring Security | 3.x | Autenticação e autorização stateless com JWT |
| Spring Data JPA | 3.x | Camada de persistência com Hibernate |
| Flyway | 9.x | Versionamento e migrações de banco de dados |
| H2 / PostgreSQL / MySQL | - | Banco de dados (configurável por ambiente) |
| Lombok | 1.18+ | Redução de boilerplate no código |
| Maven | 3.8+ | Gerenciamento de dependências e build |
| JWT | - | Tokens para autenticação stateless |

## ✨ Funcionalidades

- ✅ **CRUD completo de Filmes** - Criar, listar, atualizar e deletar filmes
- ✅ **Gerenciamento de Categorias** - Organização por gêneros cinematográficos
- ✅ **Sistema de Usuários** - Cadastro e autenticação de usuários
- ✅ **Autenticação JWT** - Segurança stateless com tokens
- ✅ **Controle de Acesso** - Autorização baseada em roles (ADMIN, USER)
- ✅ **Versionamento de BD** - Migrações automáticas com Flyway
- ✅ **Validação de Dados** - Bean Validation nas entidades e DTOs
- ✅ **Tratamento de Exceções** - Respostas de erro padronizadas
- ✅ **Paginação e Ordenação** - Listagens otimizadas


## 📦 Pré-requisitos

Antes de começar, você precisará ter instalado:

- **Java JDK 17** ou superior
- **Maven 3.8+**
- **Git**
- **PostgreSQL** (opcional, para produção) ou use H2 em memória

## ⚙️ Instalação e Configuração

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/movieflix.git
cd movieflix

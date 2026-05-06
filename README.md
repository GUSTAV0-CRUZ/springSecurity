# 🛡️ Spring Security & JWT - Laboratório Prático

![Status](https://img.shields.io/badge/status-concluído-success)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-6DB33F)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-6DB33F)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth0-black?logo=JSON%20web%20tokens)

Este projeto é um laboratório prático desenvolvido com o objetivo exclusivo de estudar e implementar **Autenticação e Autorização** em APIs RESTful utilizando **Spring Security**, **JWT (JSON Web Tokens)** e **PostgreSQL**.

> **Nota sobre o escopo:** Como o foco absoluto deste projeto foi entender a engine do Spring Security e o fluxo de tokens, tratamentos de exceções globais (Handlers) e validações complexas de dados foram propositalmente omitidos para manter a simplicidade arquitetural.

## 🧠 Arquitetura e Implementações

O projeto foi estruturado em camadas (`controllers`, `services`, `repositories`, `entities`, `filters`, `configs`) e aplica os seguintes conceitos:

*   **Autenticação Stateless:** Configuração do `SecurityFilterChain` desativando o CSRF e gerenciando as sessões sem estado de forma nativa.
*   **Password Encoding:** Utilização do `BCryptPasswordEncoder` para garantir que nenhuma senha seja salva em texto plano no banco de dados.
*   **RBAC (Role-Based Access Control):** Autorização baseada em cargos de usuário (`ROLE_COMMON` e `ROLE_ADMIN`) utilizando Enums.
*   **Geração e Validação de JWT:** Integração com a biblioteca `com.auth0.jwt` (versão 4.4.0) utilizando o algoritmo HMAC256 para emitir tokens no login e validá-los nas requisições subsequentes.
*   **Filtro Customizado:** Implementação do `JwtAuthenticationFilter` (herdando de `OncePerRequestFilter`) para interceptar requisições, extrair o token do cabeçalho `Authorization: Bearer` e injetar o usuário no `SecurityContextHolder`.

## 🔐 Endpoints e Permissões

O sistema possui 4 endpoints principais, separados por níveis de acesso:

### 🔓 Públicos (Não requerem Token)
*   `POST /auth/register` - Registra um novo usuário no sistema com senha criptografada.
*   `POST /auth/login` - Autentica o usuário e retorna o Token JWT gerado pela Auth0.

### 🔒 Protegidos (Requerem Token Bearer)
*   `GET /product/**` - Retorna a lista de produtos. **Acesso:** Usuários com a role `ROLE_COMMON` ou `ROLE_ADMIN`.
*   `POST /product/**` - Cria um novo produto. **Acesso:** Restrito apenas a usuários com a role `ROLE_ADMIN`.

## 🛠️ Stack Tecnológica

*   **Java 17**
*   **Spring Boot 4.0.x** (Web MVC, Security, Data JPA)
*   **PostgreSQL** (Driver para banco de dados relacional)
*   **Auth0 JWT** (Biblioteca de manipulação de tokens - v4.4.0)
*   **Lombok** (Redução de boilerplate code)
*   **Maven** (Gerenciador de dependências)

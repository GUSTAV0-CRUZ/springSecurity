# 🛡️ Spring Security & JWT API

![Status](https://img.shields.io/badge/status-concluído-success)
![Java](https://img.shields.io/badge/java-17%2B-blue)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-6DB33F)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?logo=JSON%20web%20tokens)

Este projeto é um laboratório de estudos focado exclusivamente em entender a fundo a implementação de autenticação e autorização no ecossistema Java, utilizando **Spring Security**, **JWT (JSON Web Tokens)** e **PostgreSQL**.

## 🎯 Objetivo do Projeto

O objetivo principal não é ter regras de negócio complexas, mas sim dominar o fluxo de segurança de uma API RESTful. O projeto demonstra como proteger endpoints, validar credenciais no banco de dados e gerenciar sessões stateless através de tokens.

## 🧠 Aprendizados e Implementações

Neste repositório, apliquei os conceitos essenciais de segurança no Spring:

- **Configuração do SecurityFilterChain:** Substituição do modelo antigo (WebSecurityConfigurerAdapter) pelo novo padrão utilizando *Lambda DSL*.
- **Autenticação Stateless:** Desativação do CSRF e configuração de gerenciamento de sessão para não guardar estado (foco em APIs REST).
- **Password Encoding:** Utilização do `BCryptPasswordEncoder` para criptografar senhas antes de salvá-las no banco de dados.
- **UserDetailsService Customizado:** Integração do Spring Security com o banco de dados (PostgreSQL) usando o Spring Data JPA para buscar os dados de login do usuário.
- **Filtro de Interceptação (Middleware):** Implementação da classe `OncePerRequestFilter` para interceptar as requisições HTTP, extrair o cabeçalho `Authorization: Bearer` e validar o JWT antes de liberar a rota.
- **Geração e Validação de Tokens:** Uso da biblioteca `jjwt` para criar tokens assinados digitalmente, definindo claims e tempo de expiração.

## 🛠️ Stack Tecnológica

- **Java** 
- **Spring Boot 3** (Spring Web, Spring Security, Spring Data JPA)
- **PostgreSQL** (Armazenamento de usuários e senhas criptografadas)
- **JWT API** (Implementação do JSON Web Token)
- **Maven**

## 🔐 Endpoints Principais

- `POST /auth/register` - Endpoint público para criar um novo usuário no banco de dados (salvando senha com BCrypt).
- `POST /auth/login` - Endpoint público para autenticar credenciais e retornar o Token JWT.
- `GET /api/private/...` - Endpoints protegidos que exigem o envio do token JWT válido no header da requisição.

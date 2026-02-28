# Banking System API 

<div >

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%23005C84.svg?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![VS Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)

</div>

## Sobre 
Esta é uma API REST para operações bancárias básicas. Desenvolvida com **Spring Boot 3.5**. A aplicação gerencia desde a criação de contas, com geração automática de agência, até transações financeiras protegidas por autenticação.

---

## Funcionalidades

* **Autenticação **: Implementação de login via JWT (Stateless) para garantir que apenas usuários autorizados realizem operações.
* **Gestão de Clientes**: Cadastro de novos usuários com persistência em banco de dados MySQL.
* **Automação de Contas**: Ao cadastrar um cliente, o sistema gera automaticamente um número de conta único (UUID) e uma agência vinculada ao ID do usuário.
* **Operações Financeiras**:
    * **Depósitos**: Atualização de saldo em tempo real.
    * **Saques**: Verificação de saldo disponível antes da execução da transação.
* **Histórico e Extrato**: Registro de transações com data e hora para controle do usuário.

---

## Arquitetura

O projeto segue a arquitetura em camadas do Spring Boot.


1.  **Controller**: Recebe as requisições HTTP e direciona para os serviços adequados.
2.  **DTO (Data Transfer Object)**: Camada de transferência de dados que protege a estrutura das entidades do banco de dados.
3.  **Service**: Camada onde reside a lógica de negócio, como a criação automática de contas.
4.  **Security**: Filtros que interceptam requisições para validar o Token JWT.
5.  **Repository**: Interface de comunicação com o MySQL via Spring Data JPA.

---

## Tecnologias Utilizadas

* **Linguagem**: Java 17
* **Framework**: Spring Boot 3.5
* **Segurança**: Spring Security e JJWT (JSON Web Token)
* **Banco de Dados**: MySQL
* **ORM**: Spring Data JPA
* **Utilitários**: Jakarta Bean Validation

---

## Endpoints Principais

### Públicos 
| Verbo | Endpoint | Descrição |
| :--- | :--- | :--- |
| POST | `/auth/login` | Autentica o usuário e retorna o Token JWT. |
| POST | `/api/customers` | Cadastra um novo cliente (Gera conta automática). |

### Protegidos 
| Verbo | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | `/api/customers/{email}` | Retorna os detalhes do cliente pelo e-mail. |
| POST | `/api/transactions/deposit` | Realiza um depósito em conta. |
| POST | `/api/transactions/withdraw` | Realiza um saque em conta. |
| GET | `/api/transactions/history` | Retorna o histórico completo de transações. |

---

## Configuração e Instalação

### Pré-requisitos
* JDK 17 ou superior.
* Instância do MySQL rodando.

### Como Rodar
1.  **Clone o projeto**:
    ```bash
    git clone https://github.com/Niicknicole/Banking_System.git
    ```
2.  **Configure o banco**:
    Ajuste as propriedades do seu MySQL no arquivo `src/main/resources/application.yml`.
3.  **Execute a aplicação**:
    ```bash
    ./mvnw spring-boot:run
    ```

---

## Aspecto técnico observado

A configuração do Spring Security para uma API Stateless foi um ponto central do projeto. Foi necessário implementar um filtro personalizado (`JwtAuthenticationFilter`) responsável por validar o token JWT a cada requisição, garantindo que operações como saques e depósitos estivessem protegidas, enquanto rotas públicas como cadastro e autenticação, permanecessem acessíveis sem token.

---


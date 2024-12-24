## 🚀 Como executar
1º - Clone o repositório `git clone git@github.com:aldcejam/WorkChronos.git`
2º - executar `docker-compose up` na raiz do projeto

**OBS:** 📌 O projeto foi desenvolvido com o objetivo do testador não precisar configurar nada, apenas executar o comando acima e testar as funcionalidades. Por este motivos que informações sensivéis como senha do banco de dados e chave de segurança estão expostas no código.

** 👤 Usuário e Senha**
Email: admin@email.com
Senha: securepassword

## ⚙️ Funcionalidades
- Registro e Login (registro apenas backend)
- Registro de pontos (entrada, saída e pausas)
- Timeline do dia atual

## 🛠️ Tecnologias

### Backend
O projeto utiliza as seguintes tecnologias no backend:

- **Java**: versão 17  
- **Spring Boot**: versão 3.4.0  
- **Gradle**: versão configurada no wrapper  
- **PostgreSQL**: driver para integração com banco de dados  

#### Bibliotecas e Frameworks Principais
- **Data JPA:** Integração com bancos de dados via JPA.
- **Security:** Autenticação e autorização.
- **Validation:** Validação de dados com anotações.
- **Web:** APIs RESTful e servidores embutidos.
- **Lombok:** Reduz código boilerplate.

## Frontend
- **Angular:** versão 19.0.0
- **TailwindCSS:** versão 3.4.17
- **TypeScript:** versão 5.6.2 

#### 📦 Dependências principais
- **Luxon:** versão 3.5.0 (para manipulação de datas e horários)
- **Ngx-Toastr:** versão 19.0.0 (para exibição de notificações e alertas).
- **RxJS:** versão 7.8.0 (para programação reativa com observables).
- **JS-Cookie:** versão 3.0.5 (para gerenciamento de cookies).

## Funcionalidades extras pensadas
- **Resgistro de informações trabalhistas:** como salário, horas trabalhadas por dia, horas extras, banco de horas, etc. (está no DER, mas não foi implementado)
- **Relatórios:** para visualização de informações trabalhistas, como horas trabalhadas, horas extras, banco de horas, etc. (está no DER, mas não foi implementado)
- **Listagem de usuário para administradores:** para que possam visualizar informações de outros usuários (está no DER, mas não foi implementado)

## Debitos técnicos
- Para gerenciamento de objetos de valores (value objects) de gestão de tempo (data de entrada, saída e pausas) foi utilizado o tipo String no banco de dado, mas o ideal seria usar jsonb devido a sua facilidade de manipulação e consulta.
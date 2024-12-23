


## 🛠️ Tecnologias

### Backend
O projeto utiliza as seguintes tecnologias no backend:

- **Java**: versão 17  
- **Spring Boot**: versão 3.4.0  
- **Gradle**: versão configurada no wrapper  
- **PostgreSQL**: driver para integração com banco de dados  

#### Bibliotecas e Frameworks Principais
- Spring Boot Starter Data JPA  
- Spring Boot Starter Security  
- Spring Boot Starter Validation  
- Spring Boot Starter Web  
- Lombok  

## Funcionalidades
- Registro e Login (registro apenas backend)
- Registro de pontos

## Como executar
apenas executar `docker-compose up` na raiz do projeto
**OBS:** O projeto foi desenvolvido com o objetivo do testador não precisar configurar nada, apenas executar o comando acima e testar as funcionalidades. Por este motivos que informações sensivéis como senha do banco de dados e chave de segurança estão expostas no código.
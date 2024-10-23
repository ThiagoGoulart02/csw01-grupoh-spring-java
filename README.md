
# Trabalho T1 - Construção de Software

## 👥 Alunos do Grupo H

- **Marcello Marcon**
- **Otávio Cunha**
- **Rodrigo Lopes**
- **Thiago Goulart**

> **Linguagem e Framework:** Java / Spring

---

## Modelo de Entidades

Abaixo está o diagrama que representa o modelo de dados utilizado na aplicação:

![Diagrama do banco de dados](./assets/modelo_banco.png)

---

## 🚀 Rodando a Aplicação

Siga os passos abaixo para rodar a aplicação localmente usando Docker.

### **Pré-requisitos**

- **Docker** instalado em sua máquina.
- Ter o Java na versão 21.
- Ter o terraform baixado.
- Ter a AWS CLI baixada para ter acesso as credencias.
- Um editor de código, como **VSCode** ou **IntelliJ IDEA**.

### **Passo a Passo**

1. **Clonar o repositório**

   Clone o repositório do projeto com o seguinte comando:

   ```bash
   git clone https://github.com/ThiagoGoulart02/csw01-grupoh-spring-java.git
   ```

2. **Entrar na pasta do projeto**

   Após o clone, navegue até o diretório do projeto:

   ```bash
   cd csw01-grupoh-spring-java
   ```
3. **Para rodar o Terraform**
   É necessário estar com as credentials da aws devidamente configuradas na sua máquina, isso implica em, entrar na pasta .aws e trocar as credencias com base no launcher aws.
   Depois, basta entrar na pasta infra do projeto e rodar o script de ec2_setup
      ```bash
   cd infra
   ```
   ```bash
   ./ec2_setup.sh  
   ```   
---

### 📝 Observação

Para acessar o Swagger tem que pegar o Ip público da Ec2 e utilizar o protocolo http da seguinte forma

http://IpPublico:8080/swagger-ui/index.html

---


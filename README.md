
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

2. **Entrar na pasta do projeto de infra**

   Após o clone, navegue até o diretório do projeto:

   ```bash
   cd csw01-grupoh-spring-java/infra
   ```
3. **Subir a ECS pelo terraform**

   Porém antes deve ir no arquivo variables.tf e alterar a seguinte parte
   arn:aws:iam::{AWS_ID}:role/LabRole

   
   Esse local deve ser trocado pelo seu iam_role:
   ```
   variable "execution_role_arn" {
     description = ""
     type = string
     default = "arn:aws:iam::930082020931:role/LabRole"
   }
   ```

   Após isso basta rodar os comandos do terraform
   ```bash
   terraform init
   terraform plan
   terraform apply -auto-approve
   ```

### 📝 Observação

Para acessar o Swagger tem que pegar o Ip público da aplicação no serviço do ECS e utilizar o protocolo http da seguinte forma

http://IpPublico:8080/swagger-ui/index.html

---


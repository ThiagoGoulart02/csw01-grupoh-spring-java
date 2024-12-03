#!/bin/bash

# Parar o script em caso de erro
set -e

# Configurações Básicas
AWS_REGION="us-east-1"
AWS_ACCOUNT_ID="930082020931"
BACKEND_IMAGE_NAME="backend"
DB_IMAGE_NAME="postgres"

# Login no Amazon ECR
echo "Realizando login no Amazon ECR..."
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

# Criar repositórios no ECR (se ainda não existirem)
echo "Criando repositórios no ECR..."
aws ecr create-repository --repository-name $BACKEND_IMAGE_NAME || echo "Repositório $BACKEND_IMAGE_NAME já existe"
aws ecr create-repository --repository-name $DB_IMAGE_NAME || echo "Repositório $DB_IMAGE_NAME já existe"

# Compilar o Projeto Backend com Maven
# Navegar até o diretório raiz do projeto onde está o pom.xml
cd ..

# Compilar o Projeto Backend com Maven
echo "Compilando o projeto com Maven..."
mvn clean package -DskipTests

# Construir e enviar a imagem do Backend para o ECR
echo "Construindo e enviando a imagem Docker do Backend..."
docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$BACKEND_IMAGE_NAME:latest .
docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$BACKEND_IMAGE_NAME:latest

# Construir e enviar a imagem do Banco de Dados para o ECR
echo "Construindo e enviando a imagem Docker do Banco de Dados..."
docker build -t $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$DB_IMAGE_NAME:latest -f Dockerfile.db .
docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$DB_IMAGE_NAME:latest

# Atualizar variáveis no Terraform (opcional, caso as imagens sejam dinâmicas)
echo "Atualizando o arquivo terraform.tfvars..."
echo "app_image=\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$BACKEND_IMAGE_NAME:latest\"" > terraform.tfvars
echo "db_image=\"$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$DB_IMAGE_NAME:latest\"" >> terraform.tfvars

# Aplicar configurações no Terraform (infraestrutura ECS e rede)
cd infra

echo "Criando a infraestrutura no Terraform..."
terraform init
terraform apply -auto-approve

echo "Deploy concluído com sucesso!"

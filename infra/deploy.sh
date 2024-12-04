#!/bin/bash

# Parar o script em caso de erro
set -e

# Configurações Básicas
DOCKER_USERNAME="rodrigolopesmichalski"  # Substitua pelo seu usuário no Docker Hub
BACKEND_IMAGE_NAME="backend"
DB_IMAGE_NAME="postgres"
DOCKERHUB_REPO_BACKEND="$DOCKER_USERNAME/$BACKEND_IMAGE_NAME"
DOCKERHUB_REPO_DB="$DOCKER_USERNAME/$DB_IMAGE_NAME"
AWS_REGION="us-east-1"
AWS_ACCOUNT_ID="930082020931"

# Login no Amazon ECR
echo "Realizando login no Amazon ECR..."
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
# Construir e enviar a imagem do Backend para o Docker Hub

cd ..
echo "Construindo e enviando a imagem Docker do Backend..."
docker build -t $DOCKERHUB_REPO_BACKEND:latest .
docker push $DOCKERHUB_REPO_BACKEND:latest

# Construir e enviar a imagem do Banco de Dados para o Docker Hub
echo "Construindo e enviando a imagem Docker do Banco de Dados..."
docker build -t $DOCKERHUB_REPO_DB:latest -f Dockerfile.db .
docker push $DOCKERHUB_REPO_DB:latest

# Aplicar configurações no Terraform (infraestrutura ECS e rede)
cd infra

echo "Criando a infraestrutura no Terraform..."
terraform init
terraform apply -auto-approve

echo "Deploy concluído com sucesso!"

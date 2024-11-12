#!/bin/bash

echo "Inicializando o Terraform..."
terraform init

echo "Aplicando a infraestrutura..."
terraform apply -auto-approve

echo "Obtendo o IP da instância EC2..."
EC2_PUBLIC_IP=$(terraform output -raw aws_instance_public_ip)

echo "IP da EC2: $EC2_PUBLIC_IP"

#PEM_FILE="$(pwd)/ec2-key.pem"

#
#echo "Aguardando a instância EC2 iniciar..."
#sleep 10  # Aguarde 30 segundos para garantir que a instância esteja pronta
#
#echo "Definindo permissões para a chave SSH..."
#chmod 400 "$PEM_FILE"
#
#echo "Conectando à instância EC2 via SSH..."
#ssh -i "$PEM_FILE" -o StrictHostKeyChecking=no ubuntu@$EC2_PUBLIC_IP

# ssh -i "ec2-kem.pem" -o StrictHostKeyChecking=no ubuntu@$EC2_PUBLIC_IP

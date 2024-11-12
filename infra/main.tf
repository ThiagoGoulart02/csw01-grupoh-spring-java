provider "aws" {
  region = "us-east-1"
}

resource "aws_instance" "ec2" {
  ami                    = "ami-0866a3c8686eaeeba"
  instance_type          = "t2.micro"
  vpc_security_group_ids = [aws_security_group.api_access.id]
  key_name               = aws_key_pair.key_pair.key_name
  tags = {
    Name = "ec2"
  }
  user_data = <<-EOF
              #!/bin/bash
              # Update package index
              sudo apt-get update -y
              sudo ufw allow 8080

              # Install Docker

              sudo apt-get update
              sudo apt-get install ca-certificates curl
              sudo install -m 0755 -d /etc/apt/keyrings
              sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
              sudo chmod a+r /etc/apt/keyrings/docker.asc

              # Add the repository to Apt sources:
              echo \
                "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
                $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
                sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
              sudo apt-get update

              # Install Docker Engine
              sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

              # Start Docker service
              sudo systemctl start docker
              sudo systemctl enable docker

              # Clone github
              mkdir repo
              cd repo
              git clone https://github.com/ThiagoGoulart02/csw01-grupoh-spring-java.git

              # Enter repo
              cd csw01-grupoh-spring-java
              cd docker

              # Build Docker image
              sudo docker compose up -d


              EOF

}
resource "aws_security_group" "api_access" {
  name        = "API-security-group-T1"
  description = "Security group para permitir SSH, HTTP e HTTPS"

  # Regra de entrada para SSH (porta 22)
  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Regra de entrada para HTTP (porta 80)
  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Regra de entrada para HTTPS (porta 443)
  ingress {
    description = "HTTPS"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "api port"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "pg port"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "pgAdmin port"
    from_port   = 5050
    to_port     = 5050
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Regra de saída que permite todo o tráfego
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_s3_bucket" "my_bucket" {
  bucket = "csw01-grupoh-spring-java"

  tags = {
    Name        = "S3"
    Environment = "Production"
  }
}

# Exemplo de política de bucket para permitir acesso público de leitura (ajuste conforme necessário)
resource "aws_s3_bucket_policy" "bucket_policy" {
  bucket = aws_s3_bucket.my_bucket.id
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect    = "Allow"
        Principal = "*"
        Action    = "s3:GetObject"
        Resource  = "${aws_s3_bucket.my_bucket.arn}/*"
      }
    ]
  })
}

# Criação da ssh keypair diretamente no terraform
resource "tls_private_key" "my_key" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "key_pair" {
  key_name   = "ec2-key"
  public_key = tls_private_key.my_key.public_key_openssh
}

resource "local_file" "my_key_pem" {
  filename        = "${path.module}/ec2-key.pem"
  content         = tls_private_key.my_key.private_key_pem
  file_permission = "0400"
}

output "private_key_pem" {
  value     = tls_private_key.my_key.private_key_pem
  sensitive = true
}

output "aws_instance_public_ip" {
  value = aws_instance.ec2.public_ip
}
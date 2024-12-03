variable "vpc_cidr" {
  description = "CIDR block da VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "public_subnet_cidr" {
  description = "CIDR block da subnet pública"
  type        = string
  default     = "10.0.1.0/24"
}

variable "app_image" {
  description = "Imagem do backend para o ECS (registrada no ECR)"
  type        = string
  default     = "930082020931.dkr.ecr.us-east-1.amazonaws.com/meu-backend:latest"
}

variable "db_image" {
  description = "Imagem do banco de dados para o ECS (registrada no ECR)"
  type        = string
  default     = "930082020931.dkr.ecr.us-east-1.amazonaws.com/meu-postgres:latest"
}

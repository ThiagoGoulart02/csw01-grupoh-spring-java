variable "db_image" {
  description = "Imagem do banco de dados para o ECS (registrada no ECR)"
  type        = string
  default     = "930082020931.dkr.ecr.us-east-1.amazonaws.com/postgres:latest"
}

variable "backend_image" {
  description = "Imagem do banco de dados para o ECS (registrada no ECR)"
  type        = string
  default     = "930082020931.dkr.ecr.us-east-1.amazonaws.com/backend:latest"
}


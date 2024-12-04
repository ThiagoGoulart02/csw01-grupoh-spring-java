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
  default     = "rodrigolopesmichalski/backend:latest"
}

variable "db_image" {
  description = "Imagem do banco de dados para o ECS (registrada no ECR)"
  type        = string
  default     = "rodrigolopesmichalski/postgres:latest"
}

variable "execution_role_arn" {
  description = ""
  type = string
  default = "arn:aws:iam::930082020931:role/LabRole"
}

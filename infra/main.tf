

# VPC
resource "aws_vpc" "my_vpc" {
  cidr_block = var.vpc_cidr
  enable_dns_support   = true
  enable_dns_hostnames = true
  tags = {
    Name = "my-vpc"
  }
}

# Subnet Pública
resource "aws_subnet" "public_subnet" {
  vpc_id                  = aws_vpc.my_vpc.id
  cidr_block              = var.public_subnet_cidr
  map_public_ip_on_launch = true

  tags = {
    Name = "public-subnet"
  }
}

# Internet Gateway
resource "aws_internet_gateway" "my_igw" {
  vpc_id = aws_vpc.my_vpc.id

  tags = {
    Name = "my-internet-gateway"
  }
}

resource "aws_route_table" "public_route_table" {
  vpc_id = aws_vpc.my_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.my_igw.id
  }

  tags = {
    Name = "public-route-table"
  }
}
# Associar Subnet Pública ao Route Table
resource "aws_route_table_association" "public_subnet_assoc" {
  subnet_id      = aws_subnet.public_subnet.id
  route_table_id = aws_route_table.public_route_table.id
}

# Security Group para ECS
resource "aws_security_group" "ecs_security_group" {
  name        = "ecs-security-group"
  description = "Security group for ECS"
  vpc_id      = aws_vpc.my_vpc.id

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.my_vpc.cidr_block]
  }

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.my_vpc.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "ecs-security-group"
  }
}

# ECS Cluster
resource "aws_ecs_cluster" "my_cluster" {
  name = "my-ecs-cluster"
}

# Task Definition para o Backend
resource "aws_ecs_task_definition" "app_task" {
  family                   = "spring-boot-app"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "256"
  memory                   = "512"

  execution_role_arn = "arn:aws:iam::930082020931:role/LabRole"

  container_definitions = jsonencode([{
    name        = "spring-boot-app"
    image       = var.app_image
    memory      = 512
    cpu         = 256
    essential   = true
    portMappings = [{
      containerPort = 8080
      hostPort      = 8080
      protocol      = "tcp"
    }]
    healthCheck = {
      command     = ["CMD-SHELL", "curl -f http://localhost:8080 || exit 1"]
      interval    = 30
      timeout     = 5
      retries     = 3
      startPeriod = 60
    }
    environment = [
      { name = "SPRING_DATASOURCE_URL", value = "jdbc:postgresql://postgres:5432/mydatabase" },
      { name = "SPRING_DATASOURCE_USERNAME", value = "admin" },
      { name = "SPRING_DATASOURCE_PASSWORD", value = "admin" }
    ]
  }])
}

# Task Definition para o Banco de Dados
resource "aws_ecs_task_definition" "db_task" {
  family                   = "postgres-db"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "256"
  memory                   = "512"

  execution_role_arn = "arn:aws:iam::930082020931:role/LabRole"

  container_definitions = jsonencode([{
    name        = "postgres"
    image       = var.db_image
    memory      = 512
    cpu         = 256
    essential   = true
    portMappings = [{
      containerPort = 5432
      hostPort      = 5432
      protocol      = "tcp"
    }]
    environment = [
      { name = "POSTGRES_USER", value = "admin" },
      { name = "POSTGRES_PASSWORD", value = "admin" },
      { name = "POSTGRES_DB", value = "mydatabase" }
    ]
  }])
}

# ECS Service para o Backend
resource "aws_ecs_service" "app_service" {
  name            = "spring-boot-service"
  cluster         = aws_ecs_cluster.my_cluster.id
  task_definition = aws_ecs_task_definition.app_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = [aws_subnet.public_subnet.id]
    security_groups  = [aws_security_group.ecs_security_group.id]
    assign_public_ip = true
  }
}

# ECS Service para o Banco de Dados
resource "aws_ecs_service" "db_service" {
  name            = "postgres-service"
  cluster         = aws_ecs_cluster.my_cluster.id
  task_definition = aws_ecs_task_definition.db_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = [aws_subnet.public_subnet.id]
    security_groups  = [aws_security_group.ecs_security_group.id]
    assign_public_ip = false
  }
}

# Outputs
output "vpc_id" {
  value = aws_vpc.my_vpc.id
}

output "app_service_name" {
  value = aws_ecs_service.app_service.name
}

output "db_service_name" {
  value = aws_ecs_service.db_service.name
}

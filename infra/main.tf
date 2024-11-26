provider "aws" {
  region = "us-east-1" # Região onde o RDS será criado
}

resource "aws_db_instance" "rds_postgresql" {
  identifier              = var.db_identifier
  allocated_storage       = 20
  engine                  = "postgres"
  engine_version          = var.db_engine_version
  instance_class          = var.db_instance_class
  db_name                 = var.db_name
  username                = var.db_username
  password                = var.db_password
  publicly_accessible     = true
  skip_final_snapshot     = true
  backup_retention_period = 7
  multi_az                = false
  storage_type            = "gp2"
  vpc_security_group_ids  = [aws_security_group.rds_sg.id]
}

resource "aws_security_group" "rds_sg" {
  name        = "rds-sg"
  description = "Permitir acesso ao RDS"

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # Permite acesso de qualquer IP
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
output "rds_endpoint" {
  value       = aws_db_instance.rds_postgresql.endpoint
  description = "Endpoint do banco de dados RDS PostgreSQL"
}
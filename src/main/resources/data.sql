-- Limpando tabelas existentes
DELETE FROM notification_preferences;
DELETE FROM transactions;
DELETE FROM tickets;
DELETE FROM users;
DELETE FROM events;
DELETE FROM tenants;

INSERT INTO tenants (tenant_id, name, contact, specific_configuration)
VALUES 
    (1, 'Franco Cristaldo', '99999999999', 'a'),
    (2, 'Maria da Silva', '88888888888', 'b');

INSERT INTO events (event_id, tenant_id, name, date, location)
VALUES 
    (1, 1, 'Lançamento de Produto', '2024-12-10', 'São Paulo'),
    (2, 2, 'Conferência Anual', '2024-12-15', 'Rio de Janeiro');

INSERT INTO users (user_id, tenant_id, name, email, phone, role)
VALUES 
    (1, 1, 'João Pedro', 'joao@example.com', '11999999999', 'admin'),
    (2, 1, 'Ana Clara', 'ana@example.com', '21999999999', 'user'),
    (3, 2, 'Carlos Alberto', 'carlos@example.com', '32999999999', 'admin');

INSERT INTO tickets (ticket_id, user_id, event_id, status, purchase_date)
VALUES 
    (1, 1, 1, 'confirmed', '2024-12-01'),
    (2, 2, 1, 'pending', '2024-12-02'),
    (3, 3, 2, 'confirmed', '2024-12-03');

INSERT INTO transactions (transaction_id, user_id, amount, status, transaction_date)
VALUES 
    (1, 1, 100.50, 'completed', '2024-12-01'),
    (2, 2, 200.75, 'pending', '2024-12-02'),
    (3, 3, 150.00, 'completed', '2024-12-03');

INSERT INTO notification_preferences (preference_id, user_id, preference_type, enabled)
VALUES 
    (1, 1, 'email', true),
    (2, 1, 'sms', false),
    (3, 2, 'email', true),
    (4, 3, 'sms', true);

DELETE FROM transactions;
DELETE FROM tickets;
DELETE FROM users;
DELETE FROM events;
DELETE FROM tenants;

INSERT INTO tenants (tenant_id, name, contact, specific_configuration) VALUES (1, 'Franco Cristaldo', '99999999999', 'a');
-- Users Table (compatible with User Module)
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Support Teams (Governance)
CREATE TABLE IF NOT EXISTS support_teams (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SLA Policies
CREATE TABLE IF NOT EXISTS sla_policies (
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    priority VARCHAR(10) NOT NULL,
    response_hours INTEGER NOT NULL,
    resolution_hours INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(category, priority)
);

-- Complaints (Unified Core Table)
CREATE TABLE IF NOT EXISTS complaints (
    id BIGSERIAL PRIMARY KEY,
    ticket_id VARCHAR(50) UNIQUE NOT NULL,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    category VARCHAR(100) NOT NULL,
    sub_category VARCHAR(100),
    priority VARCHAR(10) NOT NULL,
    status VARCHAR(20) DEFAULT 'OPEN',
    description TEXT,
    department_id BIGINT REFERENCES support_teams(id),
    assigned_agent_id BIGINT,
    sla_response_deadline TIMESTAMP,
    sla_resolution_deadline TIMESTAMP,
    sla_penalty_points INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Initial Demo Data
INSERT INTO support_teams (name, description)
VALUES
('IT Support', 'Handles technical and system issues'),
('Finance', 'Handles billing, payments, and refunds'),
('Operations', 'Handles delivery and service quality'),
('Customer Service', 'Handles account and access issues')
ON CONFLICT (name) DO NOTHING;

INSERT INTO sla_policies (category, priority, response_hours, resolution_hours)
VALUES
('Technical Issues', 'P0', 1, 4),
('Technical Issues', 'P1', 2, 8),
('Billing and Payments', 'P1', 4, 24),
('Service Delivery', 'P2', 8, 48)
ON CONFLICT (category, priority) DO NOTHING;

INSERT INTO users (name, email, password_hash, role)
VALUES ('Admin User', 'admin@cms.com', '$2a$10$dummyhash', 'ADMIN')
ON CONFLICT (email) DO NOTHING;

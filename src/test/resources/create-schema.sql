CREATE TABLE if not exists users (
	date_joined TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	user_id INT NOT NULL AUTO_INCREMENT,
	email varchar(255) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id),
	CONSTRAINT email_unique UNIQUE (email)
);

-- Create 'documents' table
CREATE TABLE if not exists documents (
	document_id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	document_name varchar(255) NULL,
	word_count INT NULL,
	CONSTRAINT documents_pkey PRIMARY KEY (document_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS teams (
    team_id INT NOT NULL AUTO_INCREMENT,
    team_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (team_id)
);

CREATE TABLE IF NOT EXISTS user_teams (
    user_id INT NOT NULL,
    team_id INT NOT NULL,
    PRIMARY KEY (user_id, team_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE
);

INSERT INTO users (date_joined, email)
VALUES
    ('2025-03-12', 'john.doe@example.com'),
    ('2025-03-11', 'jane.smith@example.com'),
    ('2025-03-10', 'alice.jones@example.com'),
    ('2025-03-09', 'bob.brown@example.com'),
    ('2025-03-08', 'carol.white@example.com');

INSERT INTO documents (date_added, user_id, document_name, word_count)
VALUES
    ('2025-03-12T10:00:00', 1, 'Project Proposal', 1024),
    ('2025-04-11T09:30:00', 2, 'Marketing Plan', 1500),
    ('2025-03-10T08:45:00', 3, 'Annual Report', 3000),
    ('2025-03-09T14:15:00', 4, 'Meeting Minutes', 500),
    ('2025-03-08T11:00:00', 5, 'Research Paper', 2500);


INSERT INTO teams (team_name)
VALUES
    ('Team1'),
    ('Team2');



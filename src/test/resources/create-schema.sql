CREATE TABLE if not exists users (
	date_joined date NULL,
	user_id int NOT NULL,
	email varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

-- Create 'documents' table
CREATE TABLE if not exists documents (
	date_added datetime NULL,
	document_id int NOT NULL,
	user_id int NULL,
	document_name varchar(255) NULL,
	word_count varchar(255) NULL,
	document_text text NULL,
	CONSTRAINT documents_pkey PRIMARY KEY (document_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (date_joined, user_id, email)
VALUES
    ('2025-03-12', 1, 'john.doe@example.com'),
    ('2025-03-11', 2, 'jane.smith@example.com'),
    ('2025-03-10', 3, 'alice.jones@example.com'),
    ('2025-03-09', 4, 'bob.brown@example.com'),
    ('2025-03-08', 5, 'carol.white@example.com');

INSERT INTO documents (date_added, document_id, user_id, document_name, word_count, document_text)
VALUES
    ('2025-03-12T10:00:00', 1, 1, 'Project Proposal', 1024, 'This is a sample document text for a project proposal. It contains important information regarding the project.'),
    ('2025-04-11T09:30:00', 2, 2, 'Marketing Plan', 1500, 'This marketing plan outlines the strategies for product promotion and brand building. The key sections include target audience analysis, budget, and timeline.'),
    ('2025-03-10T08:45:00', 3, 3, 'Annual Report', 3000, 'The annual report summarizes the company’s performance over the past year, including financial results, key milestones, and future strategies.'),
    ('2025-03-09T14:15:00', 4, 4, 'Meeting Minutes', 500, 'These are the meeting minutes from the quarterly review. Topics discussed include project updates, resource allocation, and action items.'),
    ('2025-03-08T11:00:00', 5, 5, 'Research Paper', 2500, 'This research paper explores the latest advancements in AI technology. It covers algorithms, case studies, and future trends in artificial intelligence.');



CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE employee
(
	employee_id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
	first_name VARCHAR(32) NOT NULL,
	last_name VARCHAR(32) NOT NULL,
	contact_email VARCHAR(64),
	company_email VARCHAR(64) NOT NULL,
	birth_date VARCHAR(32) NOT NULL,
	hired_date VARCHAR(32) NOT NULL,
	created_at timestamp default current_timestamp	
);

CREATE TABLE address
(
        address_id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
        employee_id UUID NOT NULL,
        street VARCHAR(64) NOT NULL,
        suite VARCHAR(8),
        city VARCHAR(64) NOT NULL,
        region VARCHAR(3) NOT NULL,
        postal VARCHAR(10) NOT NULL,
        country VARCHAR(2) NOT NULL,
        
        CONSTRAINT fk_address_employee FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

CREATE TABLE assigned_to
(
        assigned_to_id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
        superior_id UUID NOT NULL,
        subordinate_id UUID NOT NULL,
        
        CONSTRAINT fk_assigned_to_superior FOREIGN KEY (superior_id) REFERENCES employee (employee_id),        
        CONSTRAINT fk_assigned_to_subordinate FOREIGN KEY (subordinate_id) REFERENCES employee (employee_id)
);

CREATE TABLE field
(
        field_id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
        name VARCHAR(32) NOT NULL,
        type VARCHAR(64) NOT NULL,
        created_at timestamp default current_timestamp
);

CREATE TABLE skill
(
        skill_id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
        employee_id UUID NOT NULL,
        field_id UUID NOT NULL,
        experience INTEGER NOT NULL,
        summary VARCHAR(256),
        
        CONSTRAINT fk_skill_employee FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
        CONSTRAINT fk_skill_field FOREIGN KEY (field_id) REFERENCES field (field_id)
      
);

CREATE TABLE business_unit
(
        business_unit_id INTEGER PRIMARY KEY,
        business_unit VARCHAR(64) NOT NULL
);

CREATE TABLE role
(
        role_id INTEGER PRIMARY KEY,
        role VARCHAR(64) NOT NULL
);

CREATE TABLE employee_business_unit_role
(
        employee_business_unit_role_id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
        employee_id UUID NOT NULL,
        role_id INT NOT NULL,
        business_unit_id INT NOT NULL,

        CONSTRAINT fk_unit_employee FOREIGN KEY (employee_id) REFERENCES employee (employee_id),        
        CONSTRAINT fk_unit_role FOREIGN KEY (role_id) REFERENCES role (role_id),
        CONSTRAINT fk_unit_unit FOREIGN KEY (business_unit_id) REFERENCES business_unit (business_unit_id)
);

INSERT INTO role (role_id, role) VALUES (1, 'Technical Consultant');
INSERT INTO role (role_id, role) VALUES (2, 'Project Manager');
INSERT INTO role (role_id, role) VALUES (3, 'Director');
INSERT INTO role (role_id, role) VALUES (4, 'Chief');

INSERT INTO business_unit (business_unit_id, business_unit) VALUES (1, 'Digital Experience Group');
INSERT INTO business_unit (business_unit_id, business_unit) VALUES (2, 'Adobe');
INSERT INTO business_unit (business_unit_id, business_unit) VALUES (3, 'IBM NBU');
INSERT INTO business_unit (business_unit_id, business_unit) VALUES (4, 'API Management');
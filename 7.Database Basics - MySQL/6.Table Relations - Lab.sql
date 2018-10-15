/*		Lab: Table Relations
*/

/*
	1
*/
CREATE TABLE mountains(
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45)
);

CREATE TABLE peaks(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
    mountain_id INT(11),
    CONSTRAINT fk_peaks_mountains
    FOREIGN KEY (mountain_id)
    REFERENCES mountains(id)
);

/*
	2
*/
SELECT c.id,
	v.vehicle_type,
	CONCAT(c.first_name,' ',c.last_name) AS driver_id
FROM vehicles v
JOIN campers c
ON v.driver_id = c.id;

/*
	3
*/
SELECT r.starting_point AS route_starting_point,
	end_point AS route_ending_point,
    leader_id,
    concat(c.first_name, ' ', c.last_name) AS leared_name
FROM routes r
JOIN campers c
ON r.leader_id = c.id;

/*
	4
*/
CREATE TABLE mountains(
	id INT(11) AUTO_INCREMENT,
    name VARCHAR(45),
    PRIMARY KEY(id)
);

CREATE TABLE peaks(
	id INT(11) AUTO_INCREMENT,
    name VARCHAR(45),
    mountain_id INT(11),
    CONSTRAINT fk_peaks_mountains
    FOREIGN KEY(mountain_id)
    REFERENCES mountains(id)
    ON DELETE CASCADE,
    PRIMARY KEY(id)
);

/*
	5
*/

CREATE TABLE clients(
	id INT(11) AUTO_INCREMENT,
    client_name VARCHARACTER(100),
    project_id INT(11),
    PRIMARY KEY(id)
);

CREATE TABLE projects(
	id INT(11) AUTO_INCREMENT,
    client_id INT(11),
    project_lead_id INT(11),
    CONSTRAINT fk_projects_clients
    FOREIGN KEY (client_id)
    REFERENCES clients(id),
    PRIMARY KEY (id)
);

ALTER TABLE clients
ADD CONSTRAINT fk_clients_projects
FOREIGN KEY (project_id)
REFERENCES projects(id);

CREATE TABLE employees(
	id INT(11) AUTO_INCREMENT,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    project_id INT(11),
    CONSTRAINT fk_employees_projects
    FOREIGN KEY(project_id)
    REFERENCES projects(id),
    PRIMARY KEY (id)
);

ALTER TABLE projects
ADD CONSTRAINT fk_projects_employees
FOREIGN KEY (project_lead_id)
REFERENCES employees(id);

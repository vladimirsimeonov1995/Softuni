/*
	1
*/

DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(45))
RETURNS INT
BEGIN
	DECLARE e_count INT;
    SET e_count := (SELECT COUNT(e.employee_id)
					FROM employees AS e
					JOIN addresses AS a
					ON e.address_id = a.address_id
					JOIN towns AS t
					ON t.town_id = a.town_id
					WHERE t.name = town_name);
	RETURN e_count;
END $$

SELECT ufn_count_employees_by_town('Sofia') AS count;

/*
	2
*/
DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(45))
BEGIN
	UPDATE employees AS e
	JOIN d.departments 
    ON e.department_id = d.department_id
    SET e.salary = e.salary * 1.05
    WHERE d.name = department_name;
END $$

/*
	3
*/
DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INT(11))
BEGIN 
	UPDATE employees e
    SET e.salary = e.salary * 1.05
    WHERE e.employee_id = id;
END $$

























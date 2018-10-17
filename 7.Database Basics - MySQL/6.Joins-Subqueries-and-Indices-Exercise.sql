/*
	1
*/
SELECT 
	d.manager_id AS employee_id,
	CONCAT(e.first_name, ' ', e.last_name) AS full_name,
    d.department_id,
    d.name
FROM departments AS d
JOIN employees AS e
ON d.manager_id = e.employee_id
ORDER BY e.employee_id
LIMIT 5;

/*
	2
*/
SELECT 
	a.town_id,
    t.name,
	a.address_text
FROM addresses AS a
JOIN towns AS t
ON a.town_id = t.town_id
WHERE t.name IN('San Francisco','Sofia','Carnation')
ORDER BY t.town_id,a.address_id; 

/*
	3
*/
SELECT 
	e.employee_id,
    e.first_name,
    e.last_name,
    e.department_id,
    salary
FROM employees AS e
WHERE manager_id IS NULL;

/*
	4
*/
SELECT COUNT(e.employee_id)
FROM employees AS e
WHERE e.salary >(
	SELECT AVG(em.salary)
	FROM employees AS em
);






















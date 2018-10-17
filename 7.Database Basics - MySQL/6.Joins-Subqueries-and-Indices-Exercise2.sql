USE soft_uni;
/*
	1
*/
SELECT
	e.employee_id,
    e.job_title,
    e.address_id,
    a.address_text
FROM employees AS e
JOIN addresses AS a
ON e.address_id = a.address_id
ORDER BY e.address_id ASC
LIMIT 5;

/*
	2
*/
SELECT 
	e.first_name,
    e.last_name,
    t.name,
    a.address_text
FROM employees AS e
JOIN addresses AS a
ON e.address_id = a.address_id
JOIN towns AS t
ON a.town_id = t.town_id
ORDER BY e.first_name ASC, last_name
LIMIT 5;

/*
	3
*/
SELECT 
	e.employee_id,
    e.first_name,
    e.last_name,
    d.name
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE d.name = 'Sales'
ORDER BY e.employee_id DESC;

/*
	4
*/
SELECT
	e.employee_id,
    e.first_name,
    e.salary,
    d.name
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE e.salary > 15000
ORDER BY e.department_id DESC
LIMIT 5;

/*
	5
*/
SELECT 
	e.employee_id,
    e.first_name
FROM employees AS e
LEFT JOIN employees_projects AS ep
ON e.employee_id = ep.employee_id
WHERE ep.employee_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

/*
	6
*/
SELECT 
	e.first_name,
    e.last_name,
    e.hire_date,
    d.name AS dept_name
FROM employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
WHERE DATE(e.hire_date) > '1999/1/1'
AND d.name IN('Finance','Sales')
ORDER BY e.hire_date ASC;

/*
	7
*/
SELECT 
	ep.employee_id,
	e.first_name,
    p.name
FROM employees_projects AS ep
JOIN employees AS e
ON ep.employee_id = e.employee_id
JOIN projects AS p
ON ep.project_id = p.project_id
WHERE DATE(p.start_date) > '2002/0/13' 
AND p.end_date IS NULL
GROUP BY ep.employee_id
ORDER BY e.first_name ASC, p.name ASC
LIMIT 5;

/*
	8
*/
SELECT 
	ep.employee_id,
    e.first_name,
    CASE
		WHEN DATE(p.start_date) > '2004/12/31' THEN NULL
        WHEN DATE(p.start_date) < '2005/01/01' THEN p.name
	END AS project_name
FROM employees_projects AS ep
JOIN employees AS e
ON ep.employee_id = e.employee_id
JOIN projects AS p
ON ep.project_id = p.project_id 
WHERE ep.employee_id = 24
ORDER BY project_name;

/*
	9
*/
SELECT 
	e.employee_id,
    e.first_name,
    e.manager_id,
    m.first_name AS manager_name
FROM employees e
JOIN employees m
ON e.manager_id = m.employee_id
WHERE e.manager_id IN(3,7)
ORDER BY e.employee_id ASC;

/*
	10
*/
SELECT 
	e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS employee_name,
    CONCAT(m.first_name, ' ', m.last_name) AS manager_name,
    d.name AS department_name
FROM employees AS e
JOIN employees AS m
ON e.manager_id = m.employee_id
JOIN departments AS d
ON e.department_id = d.department_id
ORDER BY employee_id
LIMIT 5;

/*
	11
*/
SELECT AVG(salary) AS min_average_salary
FROM employees AS e
GROUP BY department_id
ORDER BY min_average_salary ASC
LIMIT 1;

USE geography;

/*
	12
*/
SELECT 
	c.country_code,
	m.mountain_range,
    p.peak_name,
    p.elevation
FROM countries AS c
JOIN mountains_countries AS mc
ON c.country_code = mc.country_code
JOIN mountains AS m
ON mc.mountain_id = m.id
JOIN peaks AS p
ON m.id = p.mountain_id
WHERE c.country_name = 'Bulgaria'
AND p.elevation > 2835
ORDER BY elevation DESC;

/*
	13
*/
SELECT 
	c.country_code,
    COUNT(m.id) AS mountain_range
FROM countries AS c
JOIN mountains_countries AS mc
ON c.country_code = mc.country_code
JOIN mountains AS m
ON mc.mountain_id = m.id
WHERE c.country_code IN('BG','RU','US')
GROUP BY c.country_code
ORDER BY mountain_range DESC;

/*
	14
*/
SELECT 
	c.country_name,
    r.river_name
FROM countries AS c
LEFT JOIN countries_rivers AS cr
ON c.country_code = cr.country_code
LEFT JOIN rivers AS r
ON cr.river_id = r.id
WHERE c.continent_code = 'AF'
ORDER BY c.country_name ASC
LIMIT 5;

/*
	15
*/
SELECT 
	T.con_code AS continent_code,
    T.cur_code AS currency_code,
	MAX(currency_usage) AS currency_usage
FROM(
SELECT 
	c.continent_code AS con_code,
    c.currency_code AS cur_code,
	COUNT(c.country_code) AS currency_usage
FROM countries AS c
GROUP BY currency_code, c.continent_code
) AS T
WHERE currency_usage > 1
GROUP BY T.con_code
ORDER BY continent_code,currency_code;

/*
	16
*/
SELECT 
	COUNT(c.country_code) AS country_count
FROM countries AS c
LEFT JOIN mountains_countries AS mc
ON c.country_code = mc.country_code
WHERE mc.mountain_id IS NULL;

USE soft_uni;

/*
  1
*/
SELECT first_name, last_name
FROM employees
WHERE LEFT(first_name, 2) = 'Sa';

/*
	2
*/
SELECT first_name, last_name
FROM employees
WHERE LOCATE('ei', last_name) > 0;

/*
	3
*/
SELECT first_name
FROM employees
WHERE department_id IN(3,10) AND
EXTRACT(YEAR FROM hire_date) BETWEEN 1995 AND 2005;

/*
	4
*/
SELECT first_name, last_name
FROM employees
WHERE job_title NOT LIKE '%engineer%';

/*
	5
*/
SELECT name
FROM towns
WHERE CHAR_LENGTH(name) IN (5,6)
ORDER BY name;

/*
	6
*/
SELECT town_id,name
FROM towns
WHERE LEFT(name, 1) IN('M','K','B','E')
ORDER BY name;

/*
	7
*/
SELECT town_id, name
FROM towns
WHERE LEFT(name, 1) NOT IN('B', 'R', 'D')
ORDER BY name;

/*
	8
*/
CREATE VIEW v_employees_hired_after_2000 AS
	SELECT first_name, last_name
    FROM employees
    WHERE EXTRACT(YEAR FROM hire_date) > 2000;
    
SELECT * FROM v_employees_hired_after_2000;

/*
	9
*/
SELECT first_name, last_name
FROM employees
WHERE CHAR_LENGTH(last_name) = 5;

USE geography;

/*
	10
*/
SELECT country_name, iso_code
FROM countries
WHERE country_name LIKE '%a%a%a%'
ORDER BY iso_code;

/*
	11
*/
SELECT peaks.peak_name, rivers.river_name,
	LOWER(CONCAT(peak_name,
    SUBSTRING(river_name,2))) 
    AS 'mix'
FROM peaks, rivers
WHERE RIGHT(peak_name, 1) = LEFT(river_name, 1)
ORDER BY mix;

USE diablo;

/*
	12
*/
SELECT name, DATE_FORMAT(start, '%Y-%m-%d') AS start
FROM games
WHERE EXTRACT(YEAR FROM start) IN (2011, 2012)
ORDER BY start, name
LIMIT 50;

/*
	13
*/
SELECT user_name,
	SUBSTRING(email,LOCATE('@', email)+1)
    AS 'Email Provider'
FROM users
ORDER BY `Email Provider`, user_name;

/*
	14
*/
SELECT user_name, ip_address
FROM users
WHERE ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

/*
	15
*/
SELECT name AS game,
	CASE
		WHEN EXTRACT(HOUR FROM start) BETWEEN 0 AND 11
        THEN 'Morning'
        WHEN EXTRACT(HOUR FROM start) BETWEEN 12 AND 17
		THEN 'Afternoon'
        WHEN EXTRACT(HOUR FROM start) BETWEEN 18 AND 23
        THEN 'Evening'
	END AS 'Part of the day',
	CASE
		WHEN duration <= 3 THEN 'Extra Short'
        WHEN duration > 3 AND duration <=6 THEN 'Short'
        WHEN duration >6 AND duration <= 10 THEN 'Long'
        ELSE 'Extra Long'
	END AS 'Duration'
FROM games;
USE orders;

/*
	16
*/
SELECT product_name, order_date,
	DATE_ADD(order_date, INTERVAL 3 DAY) AS 'pay_due',
    DATE_ADD(order_date, INTERVAL 1 MONTH) AS 'deliver_due'
FROM orders;



























































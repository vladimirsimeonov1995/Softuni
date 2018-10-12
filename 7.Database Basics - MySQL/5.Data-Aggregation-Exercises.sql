USE gringotts;

/*
	1
*/
SELECT COUNT(id)
FROM wizzard_deposits;

/*
	2
*/
SELECT MAX(magic_wand_size) AS 'longest_magic_wand'
FROm wizzard_deposits;

/*
	3
*/
SELECT deposit_group, 
	MAX(magic_wand_size) AS 'longest_magic_wand'
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `longest_magic_wand` ASC, deposit_group;

/*
	4
*/
SELECT deposit_group
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(magic_wand_size) ASC
LIMIT 1;

/*
	5
*/
SELECT deposit_group, ROUND(SUM(deposit_amount), 2) AS 'total_sum'
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `total_sum` ASC;

/*
	6
*/
SELECT deposit_group,
	ROUND(SUM(deposit_amount), 2) AS 'total_sum'
FROM wizzard_deposits
WHERE magic_wand_creator LIKE 'Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group;

/*
	7
*/
SELECT deposit_group,
	ROUND(SUM(deposit_amount), 2) AS 'total_sum'
FROM wizzard_deposits
WHERE magic_wand_creator LIKE 'Ollivander family'
GROUP BY deposit_group
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

/*
	8
*/
SELECT deposit_group, magic_wand_creator, 
	MIN(deposit_charge) AS 'min_deposit_charge'
FROM wizzard_deposits
GROUP BY deposit_group, magic_wand_creator
ORDER BY magic_wand_creator ASC, deposit_group ASC;

/*
	9
*/
SELECT 
CASE
	WHEN age BETWEEN 0 and 10 THEN '[0-10]'
	WHEN age BETWEEN 11 and 20 THEN '[11-20]'
	WHEN age BETWEEN 21 and 30 THEN '[21-30]'
	WHEN age BETWEEN 31 and 40 THEN '[31-40]'
	WHEN age BETWEEN 41 and 50 THEN '[41-50]'
	WHEN age BETWEEN 51 and 60 THEN '[51-60]'
	WHEN age > 60 THEN '[61+]' END
    AS age_group,
    COUNT(id) AS 'wizard_count'
FROM wizzard_deposits
GROUP BY age_group
ORDER BY `wizard_count` ASC;

/*
	10
*/
SELECT LEFT(first_name, 1) AS 'first_letter'
FROM wizzard_deposits
WHERE deposit_group LIKE 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;

/*
	11
*/
SELECT deposit_group, is_deposit_expired,
	AVG(deposit_interest) AS 'average_interest'
FROM wizzard_deposits
WHERE deposit_start_date > '1985-01-01'
GROUP BY deposit_group,is_deposit_expired
ORDER BY deposit_group DESC, is_deposit_expired ASC;

/*
	12
*/
SELECT 
    ((SELECT deposit_amount
	  FROM wizzard_deposits
	  LIMIT 1) - 
      (SELECT deposit_amount
	   FROM wizzard_deposits
	   ORDER BY id DESC
	   LIMIT 1)) AS sum_difference;


USE soft_uni;
/*
	13
*/
SELECT department_id, ROUND(MIN(salary), 4) AS 'minimum_salary'
FROM employees
WHERE hire_date > '2000-01-01'
GROUP BY department_id
HAVING department_id IN(2,5,7)
ORDER BY department_id ASC;

/*
	14
*/
CREATE TABLE newTable AS
SELECT * FROM employees
WHERE salary > 30000;

DELETE FROM newTable
WHERE manager_id LIKE 42;

UPDATE newTable
SET salary = salary + 5000
WHERE department_id = 1;

SELECT department_id, AVG(salary) AS 'avg_salary'
FROM newTable
GROUP BY department_id
ORDER BY department_id ASC;

/*
	15
*/
SELECT department_id, ROUND(MAX(salary), 4) AS 'max_salary'
FROM employees
GROUP BY department_id
HAVING `max_salary` NOT BETWEEN 30000 and 70000
ORDER BY department_id;

/*
	16
*/
SELECT COUNT(employee_id) AS ''
FROM employees
WHERE manager_id IS NULL;













































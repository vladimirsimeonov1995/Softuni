/*
	3.	Section: Data Manipulation Language (DML)
*/
 /*
	1
 */
 INSERT INTO travel_cards
(card_number,job_during_journey,colonist_id,journey_id)
SELECT 
	CASE
		WHEN c.birth_date > '1980-01-01'
        THEN CONCAT(YEAR(c.birth_date),
					DAY(c.birth_date),
					LEFT(c.ucn,4))
		ELSE CONCAT(YEAR(c.birth_date),
					MONTH(c.birth_date),
					RIGHT(c.ucn,4))
	END,
    CASE
		WHEN c.id % 2 = 0
        THEN 'Pilot'
        WHEN c.id % 3 = 0
        THEN 'Cook'
        ELSE 'Engineer'
    END,
    c.id,
    LEFT(c.ucn,1)
FROM colonists c
WHERE c.id IN(96,97,98,99,100);
 /*
	2
 */

UPDATE journeys
SET purpose =
	CASE
    WHEN id % 2 = 0 THEN 'Medical'
    WHEN id % 3 = 0 THEN 'Technical'
    WHEN id % 5 = 0 THEN 'Educational'
    WHEN id % 7 = 0 THEN 'Military'
	END
WHERE id % 2 = 0 OR id % 3 = 0 OR id % 5 = 0 OR id % 7 = 0;
/*
	3
*/
DELETE c FROM colonists c
LEFT JOIN travel_cards tc
ON c.id = tc.colonist_id
LEFT JOIN journeys j
ON j.id = tc.journey_id
WHERE j.id IS NULL
 
 
 
 
 
 
 
 
 
 
 
 
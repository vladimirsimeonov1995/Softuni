/*
	4.	Section: Querying 
*/

/*
	4
*/
SELECT 
	tc.card_number,
	tc.job_during_journey
FROM travel_cards tc
ORDER BY tc.card_number ASC;

/*
	5
*/
SELECT 
	c.id,
	CONCAT(c.first_name, ' ', last_name) AS full_name,
    c.ucn
FROM colonists c
ORDER BY c.first_name, c.last_name, c.id ASC;

/*
	6
*/
SELECT 
	j.id,
	j.journey_start,
    j.journey_end
FROM journeys j
WHERE j.purpose LIKE 'Military'
ORDER BY j.journey_start ASC;

/*
	7
*/
SELECT 
	c.id,
    CONCAT(c.first_name, ' ', c.last_name) 
    AS full_name
FROM colonists c
JOIN travel_cards tc
ON c.id = tc.colonist_id
WHERE tc.job_during_journey LIKE 'Pilot'
ORDER BY c.id ASC;

/*
	8
*/
SELECT COUNT(c.id) AS count
FROM colonists c
JOIN travel_cards tc
ON c.id = tc.colonist_id
JOIN journeys j
ON tc.journey_id = j.id
WHERE j.purpose LIKE 'Technical';

/*
	9
*/
SELECT 
	ss.name,
    sp.name
FROM spaceships ss
JOIN journeys j
ON ss.id = j.spaceship_id
JOIN spaceports sp
ON j.destination_spaceport_id = sp.id
ORDER BY ss.light_speed_rate DESC
LIMIT 1;

/*
	10
*/
SELECT 
	ss.name,
    ss.manufacturer
FROM colonists c

JOIN travel_cards tc
ON c.id = tc.colonist_id

JOIN journeys j
ON tc.journey_id = j.id

JOIN spaceships ss
ON j.spaceship_id = ss.id

WHERE c.birth_date < '1989/01/01'
GROUP BY ss.name
ORDER BY ss.name ;

/*
	11
*/
SELECT 
	p.name,
    sp.name
FROM planets p

JOIN spaceports sp
ON p.id = sp.planet_id

JOIN journeys j
ON sp.id = j.destination_spaceport_id

WHERE j.purpose LIKE 'Educational'
ORDER BY sp.name DESC;

/*
	12
*/
SELECT 
	p.name,
    COUNT(j.id) AS journeys_count
FROM planets p

JOIN spaceports sp
ON sp.planet_id = p.id

JOIN journeys j
ON sp.id = j.destination_spaceport_id

GROUP BY p.name
ORDER BY journeys_count DESC, p.name ASC;

/*
	13
*/
SELECT 
	j.id,
    p.name,
    sp.name,
    j.purpose
FROM journeys j

JOIN spaceports sp
ON j.destination_spaceport_id = sp.id

JOIN planets p
ON sp.planet_id = p.id

ORDER BY j.journey_end - j.journey_start ASC
LIMIT 1;

/*
	14
*/
SELECT 
    tc.job_during_journey AS job_name
FROM journeys j

JOIN spaceports sp
ON j.destination_spaceport_id = sp.id

JOIN planets p
ON sp.planet_id = p.id

JOIN travel_cards tc
ON tc.journey_id = j.id

GROUP BY j.id, tc.job_during_journey
ORDER BY j.journey_end - j.journey_start DESC,
COUNT(tc.id) ASC
LIMIT 1;






















/*
	5.	Section: Programmability
*/

/*
	15
*/
DELIMITER $$

CREATE FUNCTION 
udf_count_colonists_by_destination_planet 
(planet_name VARCHAR (30))
RETURNS INT
BEGIN
	DECLARE count_returned INT;
	SET count_returned := (
		SELECT COUNT(c.id)
		FROM colonists c

		JOIN travel_cards tc
		ON tc.colonist_id = c.id

		JOIN journeys j
		ON tc.journey_id = j.id

		JOIN spaceports sp
		ON j.destination_spaceport_id = sp.id

		JOIN planets p
		ON sp.planet_id = p.id

		WHERE p.name LIKE planet_name
		);
        RETURN count_returned;
END $$

/*
	16
*/
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate
(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
	DECLARE C INT;
    
    SELECT COUNT(spaceships.name) INTO C
	FROM spaceships 
	WHERE name = spaceship_name;
    
	IF (C != 1)
	THEN  
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 
        'Spaceship you are trying to modify does not exists.';
	END IF;
    
    UPDATE spaceships
    SET light_speed_rate = light_speed_rate 
		+ light_speed_rate_increse
	WHERE spaceships.name = spaceship_name;
END 



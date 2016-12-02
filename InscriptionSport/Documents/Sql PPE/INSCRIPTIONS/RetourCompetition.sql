RetourCompetition.sql


DELIMITER |
DROP PROCEDURE IF EXISTS getComp;
/* retoune toutes les competitions */
CREATE PROCEDURE getComp() 
BEGIN
SELECT * FROM `competition`;
END;|
DELIMITER ;
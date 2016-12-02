RetourCandidat.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getCand;
/* retoune toutes les candidats */
CREATE PROCEDURE getCand() 
BEGIN
SELECT * FROM `candidat`;
END;|
DELIMITER ;
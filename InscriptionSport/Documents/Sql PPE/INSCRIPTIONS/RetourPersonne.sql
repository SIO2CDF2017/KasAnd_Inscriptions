RetourPersonne.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getPers;
/* retoune toutes les personnes */
CREATE PROCEDURE getPers() 
BEGIN
SELECT * FROM `PERSONNE`, `CANDIDAT`
WHERE `PERSONNE`.`idCandidat`= `CANDIDAT`.`idCandidat`
GROUP BY `PERSONNE`.`idCandidat`;
END;|
DELIMITER ;
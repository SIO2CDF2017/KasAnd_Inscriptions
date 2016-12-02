RetourEquipe.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getEquipe;
/* retoune toutes les equipes */
CREATE PROCEDURE getEquipe() 
BEGIN
SELECT * 
FROM `EQUIPE`, `CANDIDAT`
WHERE `EQUIPE`.`idCandidat`= `CANDIDAT`.`idCandidat`
GROUP BY `EQUIPE`.`idCandidat`;
END;|
DELIMITER ;
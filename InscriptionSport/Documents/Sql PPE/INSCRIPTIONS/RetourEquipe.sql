RetourEquipe.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getEquipe;
/* retoune toutes les equipes */
CREATE PROCEDURE getEquipe() 
BEGIN
SELECT `EQUIPE`.`idCandidat`,nom
FROM `EQUIPE`, `CANDIDAT`
WHERE `EQUIPE`.`idCandidat`= `CANDIDAT`.`idCandidat`;
END; |
DELIMITER ;
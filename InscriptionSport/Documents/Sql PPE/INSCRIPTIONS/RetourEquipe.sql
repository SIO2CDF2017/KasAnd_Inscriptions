RetourEquipe.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getEquipe;
/* retoune toutes les equipes */
CREATE PROCEDURE getEquipe() 
BEGIN
SELECT * 
FROM `equipe`, `candidat`
WHERE `equipe`.`id_cand`= `candidat`.`id_cand`
GROUP BY `equipe`.`id_cand`;
END;|
DELIMITER ;
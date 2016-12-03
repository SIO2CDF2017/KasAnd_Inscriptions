RetourPersonne.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getPers;
/* retoune toutes les personnes */
CREATE PROCEDURE getPers() 
BEGIN
SELECT `personne`.`IdCandidat` AS "ID",`nom`,`prenom`,`mail` 
FROM `PERSONNE`, `CANDIDAT`
WHERE `PERSONNE`.`idCandidat`= `CANDIDAT`.`idCandidat`;
END; |
DELIMITER ;
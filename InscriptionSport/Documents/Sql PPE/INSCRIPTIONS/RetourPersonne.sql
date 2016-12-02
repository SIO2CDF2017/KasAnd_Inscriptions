RetourPersonne.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getPers;
/* retoune toutes les personnes */
CREATE PROCEDURE getPers() 
BEGIN
SELECT * FROM `personne`, `candidat`
WHERE `personne`.`id_cand`= `candidat`.`id_cand`
GROUP BY `personne`.`id_cand`;
END;|
DELIMITER ;
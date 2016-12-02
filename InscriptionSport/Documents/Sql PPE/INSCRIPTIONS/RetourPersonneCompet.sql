RetourPersonneCompet.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getIns;
/* retourne les personnes inscrite a une competition */
CREATE PROCEDURE getIns(idcomp int)
BEGIN
SELECT `CANDIDAT`.`Nom`
FROM `INSCRIRE`, `CANDIDAT`
WHERE `INSCRIRE`.`idcandidat` = `CANDIDAT`.`idcandidat`
AND `INSCRIRE`.`idCompetition` = idcomp
GROUP BY `CANDIDAT`.`Nom`;
END;|
DELIMITER ;
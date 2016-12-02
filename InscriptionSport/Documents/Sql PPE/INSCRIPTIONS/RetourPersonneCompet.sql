RetourPersonneCompet.sql

DELIMITER |
DROP PROCEDURE IF EXISTS getIns;
/* retourne les personnes inscrite a une competition */
CREATE PROCEDURE getIns(idcomp int)
BEGIN
SELECT c.nom
FROM inscrire AS si, candidat AS c
WHERE si.idcandidat = c.idcandidat
AND si.id_competition = idcomp;
GROUP BY c.nom
END;|
DELIMITER ;
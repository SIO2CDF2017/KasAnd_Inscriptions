
DELIMITER |
CREATE PROCEDURE RetourPersonnEquipe(numequipe int)

BEGIN

SELECT nom
FROM CANDIDAT, APPARTENIR
WHERE APPARTENIR.idcandidatpersonne = CANDIDAT.idcandidat
AND idcandidatequipe=numequipe;


END; | DELIMITER ;
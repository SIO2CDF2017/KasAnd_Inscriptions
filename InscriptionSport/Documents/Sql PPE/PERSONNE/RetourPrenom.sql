DELIMITER |

DROP PROCEDURE IF EXISTS RetourPrenom;
CREATE PROCEDURE RetourPrenom(Numcandidat int)

BEGIN

SELECT Prenom
FROM PERSONNE
WHERE idCandidat = Numcandidat;

END; | DELIMITER ;
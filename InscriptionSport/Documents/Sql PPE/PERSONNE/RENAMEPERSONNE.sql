DELIMITER |

DROP PROCEDURE IF EXISTS RENAMEPERSONNE;
CREATE PROCEDURE RENAMEPERSONNE(Numcandidat int, Nompersonne Varchar(25))

BEGIN

UPDATE PERSONNE
SET Prenom = Nompersonne
WHERE idCandidat = Numcandidat;

SELECT idcandidat, Prenom
FROM PERSONNE;

END; | DELIMITER ;
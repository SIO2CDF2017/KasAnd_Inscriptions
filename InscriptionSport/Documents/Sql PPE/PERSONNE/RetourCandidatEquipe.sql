RetourCandidatEquipe.sql

DELIMITER |

DROP PROCEDURE IF EXISTS RetourCandidatEquipe;
CREATE PROCEDURE RetourCandidatEquipe(Numcandidat int)

BEGIN

SELECT NomEquipe
FROM APPARTENIR,EQUIPE
WHERE idcandidatpersonne = Numcandidat
AND Appartenir.idcandidatequipe = EQUIPE.idcandidat;

END; | DELIMITER;
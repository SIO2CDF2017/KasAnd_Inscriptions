RetourCandidatEquipe.sql

DELIMITER |

CREATE PROCEDURE RetourCandidatEquipe(Numcandidat int)

BEGIN

SELECT NomEquipe
FROM APPARTENIR,EQUIPE
WHERE idcandidatpersonne = Numcandidat
AND Appartenir.idcandidatequipe = EQUIPE.idcandidat;

END; | DELIMITER;
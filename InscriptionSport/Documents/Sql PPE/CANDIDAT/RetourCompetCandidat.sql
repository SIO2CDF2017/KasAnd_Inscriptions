DELIMITER |


CREATE PROCEDURE COMPETCANDIDAT(Numcandidat int)

BEGIN

SELECT Epreuve 
FROM INSCRIRE, COMPETITION
WHERE INSCRIRE.idCompetition = COMPETITION.idCompetition
AND Idcandidat = Numcandidat;

END; | DELIMITER ;
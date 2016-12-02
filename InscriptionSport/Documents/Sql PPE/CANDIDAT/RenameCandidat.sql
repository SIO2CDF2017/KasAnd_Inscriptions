DELIMITER |

CREATE PROCEDURE RENAMECANDIDAT(Numcandidat int, Nomcandidat Varchar(25))

BEGIN

UPDATE CANDIDAT
SET Nom = Nomcandidat
WHERE idCandidat = Numcandidat;

SELECT idcandidat, Nom
FROM CANDIDAT;

END; | DELIMITER ;
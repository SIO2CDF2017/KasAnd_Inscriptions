DELIMITER |

DROP PROCEDURE IF EXISTS RetourMail;
CREATE PROCEDURE RetourMail(Numcandidat int)

BEGIN

SELECT Mail
FROM PERSONNE
WHERE idCandidat = Numcandidat;

END; | DELIMITER ;
DELIMITER |

DROP PROCEDURE IF EXISTS DELETECANDIDAT;
CREATE PROCEDURE DELETECANDIDAT(nomcandidat int)

BEGIN


DELETE FROM PERSONNE
WHERE IdCandidat = nomcandidat;
DELETE FROM EQUIPE
WHERE IdCandidat = nomcandidat;
DELETE FROM CANDIDAT
WHERE IdCandidat = nomcandidat;

SELECT 'Votre candidat est bien supprimer' AS CONFIRMATION;


END; | DELIMITER ;
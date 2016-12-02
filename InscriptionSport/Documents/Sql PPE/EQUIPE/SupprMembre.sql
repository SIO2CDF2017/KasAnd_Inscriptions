SupprMembre.sql

DELIMITER |
DROP PROCEDURE IF EXISTS supprMembrEq;
CREATE PROCEDURE supprMembrEq(id int)

BEGIN

DELETE FROM APPARTENIR
WHERE idcandidatpersonne = id;

END;
|
DELIMITER;
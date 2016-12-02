

DELIMITER |
CREATE PROCEDURE supprMembrEq(id int)

BEGIN

DELETE FROM APPARTENIR
WHERE idcandidatpersonne = id;

END;
|
DELIMITER;
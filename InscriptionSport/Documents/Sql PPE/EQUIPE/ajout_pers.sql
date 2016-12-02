DELIMITER |

 CREATE PROCEDURE ajoutPer(numpersonne int, numequipe int)


 BEGIN

 	INSERT INTO APPARTENIR(idCandidatPersonne,IdCandidatEquipe)
 	VALUES (numpersonne,numequipe);


 END; | DELIMITER ;
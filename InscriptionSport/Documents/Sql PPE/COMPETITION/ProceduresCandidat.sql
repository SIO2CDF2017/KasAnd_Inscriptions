-- Retourne le nom de la compétition.

DELIMITER | 
CREATE PROCEDURE nom_de_competition(IN NomCompetition varchar(25))
BEGIN 
	SELECT NomCompetition FROM COMPETITION 
	WHERE idcompetition = NomCompetition;

END | DELIMITER ;



-- DESINSCRIT CANDIDAT 

DELIMITER |
CREATE PROCEDURE DesinscritCandidat(IN DesinscritCandidat varchar(25))
BEGIN
	DELETE FROM inscrire
	WHERE idcandidat = DesinscritCandidat;
	END | DELIMITER ; 


-- Modifie la date de cloture des inscriptions !!!!!! dateCloture ?? ne doit pas etre dans s_inscrire pour bloquer les inscriptions ???

DELIMITER |
CREATE PROCEDURE modifdatecloture (idcompetition int, modifdatecloture date)
BEGIN
	UPDATE COMPETITION
SET date_Cloture = modifdatecloture
WHERE idcompetition = idcompetition;

END | DELIMITER ;

-- Ne pas oublier quand on ecrit la date dans Mysql de mettre des quotes sinon cest 0000-00-00

-- Modifie le nom de la compétition

DELIMITER |
CREATE PROCEDURE modifnomcompetition (idcompetition int, nomcompetition varchar(25))
BEGIN
	UPDATE COMPETITION
	SET  Epreuve = nomcompetition
	WHERE idcompetition = idcompetition;

	SELECT * from COMPETITION;
END | DELIMITER ;
 -- A FINIR PSQ LE NOM CHANGE PAS



 /* ----- COMPETITION ----- */
 
-- Inscrit un candidat de type Équipe à la compétition, paramètre: id de l'équipe et id de la compétition
DELIMITER |
CREATE PROCEDURE ajouterEquipeACompetition(idEquipe INT(4), idCompetition INT(4))
BEGIN
   
   INSERT INTO inscrire VALUES (idEquipe, idCompetition);
 
END;
|
DELIMITER ;
 
-- Inscrit un candidat de type Personne à la compétition, param: id de la personne et id de la compétition
DELIMITER |
CREATE PROCEDURE ajouterPersonneACompetition(idPersonne INT(4), idCompetition INT(4))
BEGIN
 
    INSERT INTO inscrire VALUES (idPersonne, idCompetition);
 
END;
|
DELIMITER ;
 
-- Supprime la compétition de l'application
DELIMITER |
CREATE PROCEDURE suprCom(nomComp VARCHAR(25))
BEGIN
 
    DELETE FROM COMPETITION WHERE Epreuve = nomComp;
   
END;
|
DELIMITER ;
 
 
-- Est vrai si et seulement si les inscriptions sont réservés aux équipes
 
-- Retourne l'ensemble des candidats inscrits
DELIMITER |
CREATE PROCEDURE candidatsInscrits()
BEGIN
 
    SELECT * FROM inscrire;
 
END;
|
DELIMITER ;
 
-- Retourne la date de cloture des inscriptions
DELIMITER |
CREATE PROCEDURE dateClotureInscriptions()
BEGIN
 
    SELECT date_Cloture, Epreuve FROM COMPETITION ORDER BY idcompetition;
   
END;
|
DELIMITER ;
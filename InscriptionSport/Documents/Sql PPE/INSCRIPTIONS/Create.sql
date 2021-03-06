
DELIMITER @

CREATE PROCEDURE creatComp(nom varchar(255), date_cloture date, enEqupie boolean)
BEGIN
INSERT INTO competition VALUES(nom,date_cloture,enEqupie);
END;
@
DELIMITER ;

/*CreateEquipe*/
DROP PROCEDURE creatEquipe;
DELIMITER @

CREATE PROCEDURE creatEquipe(nom varchar(255))
BEGIN
DECLARE a int;
INSERT INTO CANDIDAT (Nom) VALUES (nom);

SET a = (SELECT idCandidat FROM candidat ORDER BY idCandidat DESC LIMIT 1);

INSERT INTO equipe (idCandidat) VALUES(a);
END;
@
DELIMITER ;


/*CreatePersonne*/
DROP PROCEDURE creatPers;
DELIMITER @

CREATE FUNCTION creatPers(nom varchar(50),prenom varchar(50), mail varchar(100))
BEGIN
DECLARE a int;
INSERT INTO CANDIDAT (Nom) VALUES (nom);

SET a = (SELECT idCandidat FROM candidat ORDER BY idCandidat DESC LIMIT 1);

INSERT INTO personne (Prenom,Mail,idCandidat) VALUES(prenom,mail,a);
END;
@
DELIMITER ;
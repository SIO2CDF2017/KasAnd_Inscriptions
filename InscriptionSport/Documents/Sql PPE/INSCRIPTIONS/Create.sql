DROP PROCEDURE creatComp;
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
INSERT INTO CANDIDAT (nom_cand) VALUES (nom);

SET a = (SELECT id_cand FROM candidat ORDER BY id_cand DESC LIMIT 1);

INSERT INTO equipe (id_cand) VALUES(a);
END;
@
DELIMITER ;


/*CreatePersonne*/
DROP PROCEDURE creatPers;
DELIMITER @

CREATE FUNCTION creatPers(nom varchar(50),prenom varchar(50), mail varchar(100))
BEGIN
DECLARE a int;
INSERT INTO CANDIDAT (nom_cand) VALUES (nom);

SET a = (SELECT id_cand FROM candidat ORDER BY id_cand DESC LIMIT 1);

INSERT INTO personne (prenom,mail,id_cand) VALUES(prenom,mail,a);
END;
@
DELIMITER ;
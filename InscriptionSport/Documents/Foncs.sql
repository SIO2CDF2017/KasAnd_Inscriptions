/*CreateCompetition*/
delimiter @
DROP PROCEDURE creatComp;

CREATE PROCEDURE creatComp(nom varchar(255), date_cloture date, enEqupie boolean)
BEGIN
	INSERT INTO competition VALUES(nom,date_cloture,enEqupie);
END;
@
delimiter ;

/*CreateEquipe*/
delimiter @
DROP PROCEDURE creatEquipe;

CREATE PROCEDURE creatEquipe(nom varchar(255))
BEGIN
	INSERT INTO CANDIDAT (id_cand,nom_cand) VALUES (1,nom);
	INSERT INTO equipe (id,id_cand) VALUES(1,1);
	ALTER TABLE EQUIPE 
	ADD CONSTRAINT FK_EQUIPE_id_cand FOREIGN KEY (id_cand) REFERENCES CANDIDAT(id_cand);
END;
@
delimiter ;


/*CreatePersonne*/
delimiter @
DROP PROCEDURE creatPers;

CREATE PROCEDURE creatPers(nom varchar(50),prenom varchar(50), mail varchar(100))
BEGIN
	INSERT INTO CANDIDAT (id_cand,nom_cand) VALUES (nom);
	INSERT INTO personne (id,prenom_p,mail_p,id_cand) VALUES(1,prenom,mail,1);
	ALTER TABLE PERSONNE
	ADD CONSTRAINT FK_PERSONNE_id_cand FOREIGN KEY (id_cand) REFERENCES CANDIDAT(id_cand);
END;
@
delimiter ;


/*getCandidat*/

delimiter @
DROP PROCEDURE getcand;
CREATE FUNCTION getcand()
BEGIN
	RETURN(
	SELECT* 
	FROM CANDIDAT C, PERSONNE P, EQUIPE 
	WHERE C.id_cand = P.id_cand 
	AND C.id_cand = E.id_cand );
END	
@
delimiter ;
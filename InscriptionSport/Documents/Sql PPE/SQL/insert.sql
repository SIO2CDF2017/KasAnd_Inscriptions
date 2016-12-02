INSERT INTO CANDIDAT (IdCandidat,Nom)

VALUES

	('1','Admin'),
	('2','Liermier'),
	('3','Daurelle'),
	('4','Nguy'),	
	('5','Neto'),
	('6','Fervil'),
	('7','Tricoire'),
	('8','Pinto'),
	('9','Paris'),
	('10','Lyon');


INSERT INTO COMPETITION (IdCompetition,Epreuve,Date_Cloture,EnEquipe)

VALUES

	('1','Curling sur Gazon','2015-01-08', false),
	('2','FootBall sur Glace','2017-01-20',true),
	('3','Formule 1 en 2 Chevaux','2017-03-18', false),
	('4','Préparation du PPE','2017-01-03', true),
	('5','Mise en epreuve PPE','2017-02-08', false);

INSERT INTO PERSONNE (idCandidat,Prenom,Mail)

VALUES

	('1','Admin','Admin@Admin.fr'),
	('2','Thomas','Savoisien@Fondu.fr'),
	('3','Sebastien','Dorcelle@Producteur.com'),
	('4','Fabrice','Nike@Pompe.ch'),	
	('5','Adrien','Steam@Succes.st'),
	('6','Darwin','Noir@White.r'),
	('7','Elliot','BGTMTC@LOL.fr'),
	('8','Remy','Levieux@PremiereGuerre.gouv');

INSERT INTO EQUIPE (idCandidat,NomEquipe)

VALUES

	('9','Paris'),
	('10','Lyon');

INSERT INTO APPARTENIR (idCandidatPersonne,IdCandidatEquipe)

VALUES

	('2','9'),
	('3','9'),
	('4','10'),
	('5','10'),
	('6','9');

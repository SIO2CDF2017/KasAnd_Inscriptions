INSERT INTO CANDIDAT (IdCandidat,Nom)

VALUES

	('1','Vinciguera'),
	('2','Escriva'),
	('3','Sandolo'),
	('4','Kasperski'),	
	('5','Le Blavec'),
	('6','Gorski'),
	('7','Gorski'),
	('8','Haury'),
	('9','Montcuq'),
	('10','WTF');


INSERT INTO COMPETITION (IdCompetition,Epreuve,Date_Cloture,EnEquipe)

VALUES

	('1','Patin a nuage','2015-01-08', false),
	('2','Chasse a l abre','2017-01-20',true),
	('3','Lancé de cadavre','2017-03-18', true),
	('4','Dressage Pokemouille','2017-01-03', false),
	('5','Domptage de dragon','2017-02-08', true);

INSERT INTO PERSONNE (idCandidat,Prenom,Mail)

VALUES

	('1','Michel','Michel@dieu.fr'),
	('2','Agnes','Agnes@dragon.wow'),
	('3','Alexandre','contact@asandolo.fr'),
	('4','Victor','Victor@Pompe.ch'),	
	('5','Erwan','Erwan@Succes.st'),
	('6','Laurie','Laurue@toulouse.r'),
	('7','Stan','Stan@dieu.fr'),
	('8','Michel','Michel@haury.gouv');

INSERT INTO EQUIPE (idCandidat)

VALUES

	('9'),
	('10');

INSERT INTO APPARTENIR (idCandidatPersonne,IdCandidatEquipe)

VALUES

	('2','9'),
	('3','9'),
	('4','10'),
	('5','10'),
	('6','9');

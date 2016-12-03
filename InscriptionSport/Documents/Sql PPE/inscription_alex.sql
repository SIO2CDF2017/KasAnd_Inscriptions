-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 03 Décembre 2016 à 12:32
-- Version du serveur :  10.1.10-MariaDB
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `inscription`
--
CREATE DATABASE IF NOT EXISTS `inscription` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `inscription`;

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `ajouterEquipeACompetition`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ajouterEquipeACompetition` (`idEquipe` INT(4), `idCompetition` INT(4))  BEGIN
   
   INSERT INTO inscrire VALUES (idEquipe, idCompetition);
 
END$$

DROP PROCEDURE IF EXISTS `ajouterPersonneACompetition`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ajouterPersonneACompetition` (`idPersonne` INT(4), `idCompetition` INT(4))  BEGIN
 
    INSERT INTO inscrire VALUES (idPersonne, idCompetition);
 
END$$

DROP PROCEDURE IF EXISTS `ajoutPer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ajoutPer` (`numpersonne` INT, `numequipe` INT)  BEGIN

 	INSERT INTO APPARTENIR(idCandidatPersonne,IdCandidatEquipe)
 	VALUES (numpersonne,numequipe);


 END$$

DROP PROCEDURE IF EXISTS `candidatsInscrits`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `candidatsInscrits` ()  BEGIN
 
    SELECT * FROM inscrire;
 
END$$

DROP PROCEDURE IF EXISTS `CHANGEMAIL`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `CHANGEMAIL` (`Numcandidat` INT, `NomMail` VARCHAR(25))  BEGIN

UPDATE PERSONNE
SET Mail = NomMail
WHERE idCandidat = Numcandidat;

SELECT idcandidat, Prenom, Mail
FROM PERSONNE;

END$$

DROP PROCEDURE IF EXISTS `COMPETCANDIDAT`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `COMPETCANDIDAT` (`Numcandidat` INT)  BEGIN

SELECT Epreuve 
FROM INSCRIRE, COMPETITION
WHERE INSCRIRE.idCompetition = COMPETITION.idCompetition
AND Idcandidat = Numcandidat;

END$$

DROP PROCEDURE IF EXISTS `creatComp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creatComp` (IN `nom` VARCHAR(255), IN `date_cloture` DATE, IN `enEqupie` BOOLEAN)  BEGIN
INSERT INTO competition (Epreuve,Date_Cloture,EnEquipe) VALUES(nom,date_cloture,enEqupie);
END$$

DROP PROCEDURE IF EXISTS `creatEquipe`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creatEquipe` (`nom` VARCHAR(255))  BEGIN
DECLARE a int;
INSERT INTO CANDIDAT (Nom) VALUES (nom);

SET a = (SELECT idCandidat FROM candidat ORDER BY idCandidat DESC LIMIT 1);

INSERT INTO equipe (idCandidat) VALUES(a);
END$$

DROP PROCEDURE IF EXISTS `creatPers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creatPers` (IN `n` VARCHAR(50), IN `p` VARCHAR(50), IN `m` VARCHAR(100))  BEGIN
DECLARE a int;
INSERT INTO CANDIDAT (Nom) VALUES (n);

SET a = (SELECT idCandidat FROM candidat ORDER BY idCandidat DESC LIMIT 1);

INSERT INTO personne (Prenom,Mail,idCandidat) VALUES(p,m,a);
END$$

DROP PROCEDURE IF EXISTS `dateClotureInscriptions`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `dateClotureInscriptions` ()  BEGIN
 
    SELECT date_Cloture, Epreuve FROM COMPETITION ORDER BY idcompetition;
   
END$$

DROP PROCEDURE IF EXISTS `DELETECANDIDAT`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DELETECANDIDAT` (`nomcandidat` INT)  BEGIN


DELETE FROM PERSONNE
WHERE IdCandidat = nomcandidat;
DELETE FROM EQUIPE
WHERE IdCandidat = nomcandidat;
DELETE FROM CANDIDAT
WHERE IdCandidat = nomcandidat;

SELECT 'Votre candidat est bien supprimer' AS CONFIRMATION;


END$$

DROP PROCEDURE IF EXISTS `DesinscritCandidat`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DesinscritCandidat` (IN `DesinscritCandidat` VARCHAR(25))  BEGIN
	DELETE FROM inscrire
	WHERE idcandidat = DesinscritCandidat;
	END$$

DROP PROCEDURE IF EXISTS `getCand`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCand` ()  BEGIN
SELECT * FROM `candidat`;
END$$

DROP PROCEDURE IF EXISTS `getComp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getComp` ()  BEGIN
SELECT * FROM `competition`;
END$$

DROP PROCEDURE IF EXISTS `getEquipe`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEquipe` ()  BEGIN
SELECT `EQUIPE`.`idCandidat` As id,nom
FROM `EQUIPE`, `CANDIDAT`
WHERE `EQUIPE`.`idCandidat`= `CANDIDAT`.`idCandidat`;
END$$

DROP PROCEDURE IF EXISTS `getIns`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIns` (`idcomp` INT)  BEGIN
SELECT `CANDIDAT`.`Nom`
FROM `INSCRIRE`, `CANDIDAT`
WHERE `INSCRIRE`.`idcandidat` = `CANDIDAT`.`idcandidat`
AND `INSCRIRE`.`idCompetition` = idcomp
GROUP BY `CANDIDAT`.`Nom`;
END$$

DROP PROCEDURE IF EXISTS `getPers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPers` ()  BEGIN
SELECT `personne`.`IdCandidat` AS "ID",`nom`,`prenom`,`mail` 
FROM `PERSONNE`, `CANDIDAT`
WHERE `PERSONNE`.`idCandidat`= `CANDIDAT`.`idCandidat`;
END$$

DROP PROCEDURE IF EXISTS `modifdatecloture`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifdatecloture` (`idcompetition` INT, `modifdatecloture` DATE)  BEGIN
	UPDATE COMPETITION
SET date_Cloture = modifdatecloture
WHERE idcompetition = idcompetition;

END$$

DROP PROCEDURE IF EXISTS `modifnomcompetition`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifnomcompetition` (`idcompetition` INT, `nomcompetition` VARCHAR(25))  BEGIN
	UPDATE COMPETITION
	SET  Epreuve = nomcompetition
	WHERE idcompetition = idcompetition;

	SELECT * from COMPETITION;
END$$

DROP PROCEDURE IF EXISTS `NAMECANDIDAT`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `NAMECANDIDAT` (`Numcandidat` INT)  BEGIN

SELECT Nom
FROM CANDIDAT
WHERE IdCandidat = Numcandidat;

END$$

DROP PROCEDURE IF EXISTS `nom_de_competition`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `nom_de_competition` (IN `NomCompetition` VARCHAR(25))  BEGIN 
	SELECT NomCompetition FROM COMPETITION 
	WHERE idcompetition = NomCompetition;

END$$

DROP PROCEDURE IF EXISTS `RENAMECANDIDAT`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RENAMECANDIDAT` (`Numcandidat` INT, `Nomcandidat` VARCHAR(25))  BEGIN

UPDATE CANDIDAT
SET Nom = Nomcandidat
WHERE idCandidat = Numcandidat;

SELECT idcandidat, Nom
FROM CANDIDAT;

END$$

DROP PROCEDURE IF EXISTS `RENAMEPERSONNE`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RENAMEPERSONNE` (`Numcandidat` INT, `Nompersonne` VARCHAR(25))  BEGIN

UPDATE PERSONNE
SET Prenom = Nompersonne
WHERE idCandidat = Numcandidat;

SELECT idcandidat, Prenom
FROM PERSONNE;

END$$

DROP PROCEDURE IF EXISTS `RetourCandidatEquipe`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RetourCandidatEquipe` (`Numcandidat` INT)  BEGIN

SELECT NomEquipe
FROM APPARTENIR,EQUIPE
WHERE idcandidatpersonne = Numcandidat
AND Appartenir.idcandidatequipe = EQUIPE.idcandidat;

END$$

DROP PROCEDURE IF EXISTS `RetourMail`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RetourMail` (`Numcandidat` INT)  BEGIN

SELECT Mail
FROM PERSONNE
WHERE idCandidat = Numcandidat;

END$$

DROP PROCEDURE IF EXISTS `RetourPersonnEquipe`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RetourPersonnEquipe` (`numequipe` INT)  BEGIN

SELECT nom
FROM CANDIDAT, APPARTENIR
WHERE APPARTENIR.idcandidatpersonne = CANDIDAT.idcandidat
AND idcandidatequipe=numequipe;


END$$

DROP PROCEDURE IF EXISTS `RetourPrenom`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RetourPrenom` (`Numcandidat` INT)  BEGIN

SELECT Prenom
FROM PERSONNE
WHERE idCandidat = Numcandidat;

END$$

DROP PROCEDURE IF EXISTS `supprMembrEq`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `supprMembrEq` (`id` INT)  BEGIN

DELETE FROM APPARTENIR
WHERE idcandidatpersonne = id;

END$$

DROP PROCEDURE IF EXISTS `suprCom`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `suprCom` (`nomComp` VARCHAR(25))  BEGIN
 
    DELETE FROM COMPETITION WHERE Epreuve = nomComp;
   
END$$

--
-- Fonctions
--
DROP FUNCTION IF EXISTS `inscriptions_date`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `inscriptions_date` (`id_competition` INT) RETURNS VARCHAR(25) CHARSET latin1 BEGIN
DECLARE retour varchar(25);
DECLARE dateCloture date;
DECLARE resultat int;

SET dateCloture = (SELECT date_Cloture FROM COMPETITION WHERE idcompetition = id_competition);
SET resultat = DATEDIFF(dateCloture,(SELECT NOW()));

IF resultat > 0 THEN 
	SET retour = 'vrai';
ELSE 
	SET retour = 'faux'; 
END IF;
RETURN retour;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `appartenir`
--

DROP TABLE IF EXISTS `appartenir`;
CREATE TABLE `appartenir` (
  `IdCandidatPersonne` int(11) NOT NULL,
  `IdCandidatEquipe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `appartenir`
--

INSERT INTO `appartenir` (`IdCandidatPersonne`, `IdCandidatEquipe`) VALUES
(2, 9),
(3, 9),
(4, 10),
(5, 10),
(6, 9);

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

DROP TABLE IF EXISTS `candidat`;
CREATE TABLE `candidat` (
  `IdCandidat` int(11) NOT NULL,
  `Nom` varchar(25) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `candidat`
--

INSERT INTO `candidat` (`IdCandidat`, `Nom`) VALUES
(1, 'Vinciguera'),
(2, 'Escriva'),
(3, 'Sandolo'),
(4, 'Kasperski'),
(5, 'Le Blavec'),
(6, 'Gorski'),
(7, 'Gorski'),
(8, 'Haury'),
(9, 'Montcuq'),
(10, 'WTF'),
(11, 'test'),
(15, 'ff'),
(16, 'cc'),
(18, 'ss'),
(19, 'ss'),
(20, 'YOLO');

-- --------------------------------------------------------

--
-- Structure de la table `competition`
--

DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `IdCompetition` int(11) NOT NULL,
  `Epreuve` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `Date_Cloture` date DEFAULT NULL,
  `EnEquipe` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `competition`
--

INSERT INTO `competition` (`IdCompetition`, `Epreuve`, `Date_Cloture`, `EnEquipe`) VALUES
(1, 'Patin a nuage', '2015-01-08', 0),
(2, 'Chasse a l abre', '2017-01-20', 1),
(3, 'Lancé de cadavre', '2017-03-18', 1),
(4, 'Dressage Pokemouille', '2017-01-03', 0),
(5, 'Domptage de dragon', '2017-02-08', 1),
(6, 'Swagiste', '2017-01-24', 1),
(7, 'dd', '2023-12-12', 1);

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE `equipe` (
  `IdCandidat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `equipe`
--

INSERT INTO `equipe` (`IdCandidat`) VALUES
(9),
(10),
(20);

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

DROP TABLE IF EXISTS `inscrire`;
CREATE TABLE `inscrire` (
  `IdCandidat` int(11) NOT NULL,
  `IdCompetition` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE `personne` (
  `Prenom` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `Mail` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `IdCandidat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`Prenom`, `Mail`, `IdCandidat`) VALUES
('Michel', 'Michel@dieu.fr', 1),
('Agnes', 'Agnes@dragon.wow', 2),
('Alexandre', 'contact@asandolo.fr', 3),
('Victor', 'Victor@Pompe.ch', 4),
('Erwan', 'Erwan@Succes.st', 5),
('Laurie', 'Laurue@toulouse.r', 6),
('Stan', 'Stan@dieu.fr', 7),
('Michel', 'Michel@haury.gouv', 8),
('test', 'test', 11),
('ff', 'ff', 15),
('cc', 'cc', 16),
('ss', 'ss', 18),
('ss', 's', 19);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD PRIMARY KEY (`IdCandidatPersonne`,`IdCandidatEquipe`),
  ADD KEY `FK_APPARTENIR_IdCandidatequpe` (`IdCandidatEquipe`);

--
-- Index pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD PRIMARY KEY (`IdCandidat`);

--
-- Index pour la table `competition`
--
ALTER TABLE `competition`
  ADD PRIMARY KEY (`IdCompetition`);

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`IdCandidat`);

--
-- Index pour la table `inscrire`
--
ALTER TABLE `inscrire`
  ADD PRIMARY KEY (`IdCandidat`,`IdCompetition`),
  ADD KEY `FK_INSCRIRE_IdCompetition` (`IdCompetition`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`IdCandidat`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `candidat`
--
ALTER TABLE `candidat`
  MODIFY `IdCandidat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT pour la table `competition`
--
ALTER TABLE `competition`
  MODIFY `IdCompetition` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD CONSTRAINT `FK_APPARTENIR_IdCandidatequpe` FOREIGN KEY (`IdCandidatEquipe`) REFERENCES `candidat` (`IdCandidat`),
  ADD CONSTRAINT `FK_APPARTENIR_IdCandidatpersonne` FOREIGN KEY (`IdCandidatPersonne`) REFERENCES `candidat` (`IdCandidat`);

--
-- Contraintes pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD CONSTRAINT `FK_EQUIPE_IdCandidat` FOREIGN KEY (`IdCandidat`) REFERENCES `candidat` (`IdCandidat`);

--
-- Contraintes pour la table `inscrire`
--
ALTER TABLE `inscrire`
  ADD CONSTRAINT `FK_INSCRIRE_IdCandidat` FOREIGN KEY (`IdCandidat`) REFERENCES `candidat` (`IdCandidat`),
  ADD CONSTRAINT `FK_INSCRIRE_IdCompetition` FOREIGN KEY (`IdCompetition`) REFERENCES `competition` (`IdCompetition`);

--
-- Contraintes pour la table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `FK_PERSONNE_IdCandidat` FOREIGN KEY (`IdCandidat`) REFERENCES `candidat` (`IdCandidat`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

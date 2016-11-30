-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 30 Novembre 2016 à 09:46
-- Version du serveur :  10.1.10-MariaDB
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ins`
--
DROP DATABASE IF EXISTS `ins`;
CREATE DATABASE IF NOT EXISTS `ins` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ins`;

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

CREATE TABLE `candidat` (
  `id_cand` int(11) NOT NULL,
  `nom_cand` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `competition`
--

CREATE TABLE `competition` (
  `id_comp` int(11) NOT NULL,
  `nom_comp` varchar(255) DEFAULT NULL,
  `dateCloture` date DEFAULT NULL,
  `enEquipe` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `id_cand` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `prenom` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `id_cand` int(11) NOT NULL,
  `id_cand_EQUIPE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `s_inscire`
--

CREATE TABLE `s_inscire` (
  `id_cand` int(11) NOT NULL,
  `id_comp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD PRIMARY KEY (`id_cand`);

--
-- Index pour la table `competition`
--
ALTER TABLE `competition`
  ADD PRIMARY KEY (`id_comp`);

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id_cand`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id_cand`),
  ADD KEY `FK_PERSONNE_id_cand_EQUIPE` (`id_cand_EQUIPE`);

--
-- Index pour la table `s_inscire`
--
ALTER TABLE `s_inscire`
  ADD PRIMARY KEY (`id_cand`,`id_comp`),
  ADD KEY `FK_S_inscire_id_comp` (`id_comp`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD CONSTRAINT `FK_EQUIPE_id_cand` FOREIGN KEY (`id_cand`) REFERENCES `candidat` (`id_cand`);

--
-- Contraintes pour la table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `FK_PERSONNE_id_cand` FOREIGN KEY (`id_cand`) REFERENCES `candidat` (`id_cand`),
  ADD CONSTRAINT `FK_PERSONNE_id_cand_EQUIPE` FOREIGN KEY (`id_cand_EQUIPE`) REFERENCES `equipe` (`id_cand`);

--
-- Contraintes pour la table `s_inscire`
--
ALTER TABLE `s_inscire`
  ADD CONSTRAINT `FK_S_inscire_id_cand` FOREIGN KEY (`id_cand`) REFERENCES `candidat` (`id_cand`),
  ADD CONSTRAINT `FK_S_inscire_id_comp` FOREIGN KEY (`id_comp`) REFERENCES `competition` (`id_comp`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

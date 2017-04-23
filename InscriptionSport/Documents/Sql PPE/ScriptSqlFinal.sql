DROP DATABASE Inscription;
CREATE DATABASE Inscription;
USE Inscription;

CREATE TABLE COMPETITION(
        IdCompetition int (11) Auto_increment  NOT NULL ,
        Epreuve       Varchar (25) ,
        Date_Cloture  Date ,
        EnEquipe      boolean,
        CONSTRAINT PK_COMPETITION PRIMARY KEY (IdCompetition)
)engine = innodb;



CREATE TABLE CANDIDAT(
        IdCandidat int (11) Auto_increment  NOT NULL ,
        Nom        Varchar (25) ,
        CONSTRAINT PK_CANDIDAT PRIMARY KEY (IdCandidat)
)engine = innodb;



CREATE TABLE PERSONNE(
        Prenom     Varchar (25) ,
        Mail       Varchar (50) ,
        IdCandidat Int NOT NULL ,
        CONSTRAINT PK_PERSONNE PRIMARY KEY (IdCandidat )
)engine = innodb;



CREATE TABLE INSCRIRE(
        IdCandidat    Int NOT NULL ,
        IdCompetition Int NOT NULL ,
        CONSTRAINT PK_INSCRIRE PRIMARY KEY (IdCandidat ,IdCompetition )
)engine = innodb;



CREATE TABLE APPARTENIR(
        IdCandidatPersonne   Int NOT NULL ,
        IdCandidatEquipe Int NOT NULL ,
        CONSTRAINT PK_APPARTENIR PRIMARY KEY (IdCandidatPersonne ,IdCandidatEquipe )
)engine = innodb;



ALTER TABLE PERSONNE 
ADD CONSTRAINT FK_PERSONNE_IdCandidat FOREIGN KEY (IdCandidat) REFERENCES Candidat(IdCandidat);

ALTER TABLE INSCRIRE 
ADD CONSTRAINT FK_INSCRIRE_IdCandidat FOREIGN KEY (IdCandidat) REFERENCES Candidat(IdCandidat),
ADD CONSTRAINT FK_INSCRIRE_IdCompetition FOREIGN KEY (IdCompetition) REFERENCES Competition(IdCompetition);

ALTER TABLE APPARTENIR 
ADD CONSTRAINT FK_APPARTENIR_IdCandidatpersonne FOREIGN KEY (IdCandidatpersonne) REFERENCES Candidat(IdCandidat),
ADD CONSTRAINT FK_APPARTENIR_IdCandidatequpe FOREIGN KEY (IdCandidatequipe) REFERENCES Candidat(IdCandidat);
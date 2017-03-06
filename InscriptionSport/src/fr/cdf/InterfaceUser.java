package fr.cdf;

import fr.cdf.Utils.CommandsLigne.Action;
import fr.cdf.Utils.CommandsLigne.Option;
import fr.cdf.Utils.CommandsLigne.Menu;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author vkasperski
 */

public class InterfaceUser {
    
    
    static void AffichEquipe(Set<Equipe> e)
    {
       Inscriptions i = Inscriptions.getInscriptions();
       Set<Integer> ide = i.getIdEquipe();
       Iterator iterE = e.iterator();
       Iterator iterIde = ide.iterator();
       while(iterE.hasNext() && iterIde.hasNext())
       {
            System.out.println ("ID : "+iterIde.next()+" Nom : "+iterE.next());
       } 
    }
    
    static void AffichPers(Set<Personne> p)
    {
        Inscriptions i = Inscriptions.getInscriptions();
        Set<Integer> in = i.getIdPers();
        Iterator iterP = p.iterator();
        Iterator iterIn = in.iterator();
        while(iterP.hasNext() && iterIn.hasNext())
        {
            System.out.println ("ID : "+iterIn.next()+" Nom : "+iterP.next());
        }
    }
    
    static void AffichComp(Set<Competition> comp)
    {
        Inscriptions i = Inscriptions.getInscriptions();
        Set<Integer> in = i.getIDComp();
        Iterator iterP = comp.iterator();
        Iterator iterIn = in.iterator();
        while(iterP.hasNext() && iterIn.hasNext())
        {
            System.out.println ("ID : "+iterIn.next()+" Nom : "+iterP.next());
        }
    }
    
    
/******************************************************************************/
/********************************MENU RECHERCHE********************************/
/******************************************************************************/

    
    static Action ActionMenuRecherche()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Menu mr = new Menu("Rechercher :");
                Option SearchGetCand = new Option("Recherche de Candidats(équipes et personnes)","1",ActionMenuRechercheCand());
                Option SearchGetEqui = new Option("Recherche d'équipes","2",ActionMenuRechercheEqui());
                Option SearchGetPers = new Option("Recherche de personnes","3",ActionMenuRecherchePers());
                Option SearchGetComp = new Option("Recherche de Competitions","4",ActionMenuRechercheComp());
                Option SearchGetInsc = new Option("Recherche d'inscriptions","5",ActionMenuRechercherIns());
                mr.ajoute(SearchGetCand);
                mr.ajoute(SearchGetEqui);
                mr.ajoute(SearchGetPers);
                mr.ajoute(SearchGetComp);
                mr.ajoute(SearchGetInsc);
                mr.ajouteRevenir("r");
                mr.ajouteQuitter("q");
                mr.start(); 
            }
	};
    }
    
/*************************MENU RECHERCHE INSCROPTION***************************/
    
    static Action ActionMenuRechercherIns(){
        return new Action(){
            @Override
            public void optionSelectionnee() {
                Menu mrs = new Menu("Rechercher par :");
                Option pcand = new Option("Par candidat", "1",ActionMenuRechercheInsCand());
                Option pcomp = new Option("Par competition", "2",ActionMenuRechercheMembresComp());
                mrs.ajoute(pcand);
                mrs.ajoute(pcomp);
                mrs.ajouteRevenir("r");
                mrs.ajouteQuitter("q");
                mrs.start();
            }
            
        };
    }  
    
/********************RECHERCHER INSCRIPTIONS CANDIDATS*************************/
    
    static Action ActionMenuRechercheInsCand(){
        return new Action(){
            @Override
            public void optionSelectionnee() {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Candidat>  c1 = i.getCandidats();
                Set<Personne> p = i.getPersonnes();
                Set<Equipe> e = i.getEquipes();
                AffichPers(p);
                AffichEquipe(e);
                int IdCand = fr.cdf.Utils.InOut.getInt("Entrez l ID du candidat : ");
                Set<String> Comp = i.getinsCand(IdCand);
                Iterator in = Comp.iterator();
                while(in.hasNext())
                {
                    System.out.println("- "+in.next());
                }                
            }   
        };
    }
    
/**************************RECHERCHER CANDIDATS********************************/
    
    static Action ActionMenuRechercheCand()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Personne> p = i.getPersonnes();
                Set<Equipe> e = i.getEquipes();
                System.out.println("Personnes : ");
                AffichPers(p);
                System.out.println("Equipes : ");
                AffichEquipe(e);
                Menu rechCand = new Menu("Recherche de Candidats : ");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();                
            }
        };
    }
    
/**************************RECHERCHER EQUIPES**********************************/  
    
    static Action ActionMenuRechercheEqui()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Equipe> e = i.getEquipes();
                AffichEquipe(e);
                Menu rechCand = new Menu("Recherche d Equipes : ");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();  
            }
        };
    }

/**************************RECHERCHER PERSONNES********************************/  

     static Action ActionMenuRecherchePers()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions(); 
                Set<Personne> p = i.getPersonnes();
                AffichPers(p);
                Menu rechCand = new Menu("Recherche de Personnes : ");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();  
            }
        };
    }
     
/**************************RECHERCHER COMPETITONS******************************/
    
    static Action ActionMenuRechercheComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions(); 
                Set<Competition> comp = i.getCompetitions();
                AffichComp(comp);
                Menu rechCand = new Menu("Recherche de Personnes : ");
                Option ListCand = new Option("Voir les Personnes / Equipe Inscritent a une competition","1",ActionMenuRechercheMembresComp());
                Option ListEnEquipe = new Option("Voir la date de cloture d une competitions ","2",ActionMenuRechercheIsClo());
                rechCand.ajoute(ListCand);
                rechCand.ajoute(ListEnEquipe);
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();
            }
        };
    }

/*********************RECHERCHER MEMBRES COMPETITONS***************************/
    
    static Action ActionMenuRechercheMembresComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Competition>  c1 = i.getCompetitions();
                Competition c = i.createCompetition("compSelect", LocalDate.MAX, true);
                AffichComp(c1);
                int IdComp = fr.cdf.Utils.InOut.getInt("Entrez l ID de la competition : ");
                Set<String> Membres = c.getCandidatInscrit(IdComp);
                Iterator in = Membres.iterator();
                while(in.hasNext())
                {
                    System.out.println("- "+in.next());
                }
            }    
        };
    }
    
/*********************RECHERCHER DATE CLOTURE COMPETITION**********************/
    
    static Action ActionMenuRechercheIsClo()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                int id = fr.cdf.Utils.InOut.getInt("id de la competition : ");
                Inscriptions i = Inscriptions.getInscriptions();
                Competition c = i.createCompetition("En Equipe", LocalDate.MAX, true);
                LocalDate dateInsc = c.dateClotureInscriptions(id);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedString = dateInsc.format(formatter);
                if(c.isopen(id))
                    System.out.println("Inscription possible jusqu'au "+formattedString);
                else
                    System.out.println("Inscription expire depuis le "+formattedString); 
            }
        };
    }
  
    
/******************************************************************************/
/*************************MENU INSCRIPTION/CREATION****************************/
/******************************************************************************/
     
     
    static Action ActionMenuInscription()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Menu insc = new Menu("Inscriptions :");
                Option creatPers = new Option("nouvelle Personne","1",newPersonne());
                Option creatEqui = new Option("nouvelle Equipe","2",newEquipe());
                Option creatComp = new Option("nouvelle Competition","3",newCompetition());
                Option addPersToEqui = new Option("Ajouter une personne existante à une Equipe existante","4",AddPersToEqui());
                Option addPersToComp = new Option("Inscrire une personne existante à une competition existante","5",AddPersToComp());
                Option addEquiToComp = new Option("Inscrire une équipe existante à une competion existante","6",AddEquipeToComp());
                insc.ajoute(creatPers);
                insc.ajoute(creatEqui);
                insc.ajoute(creatComp);
                insc.ajoute(addPersToEqui);
                insc.ajoute(addPersToComp);
                insc.ajoute(addEquiToComp);
                insc.ajouteRevenir("r");
                insc.ajouteQuitter("q");
                insc.start();
            }
        };
    }

/**********************AJOUTER PERSONNE A UNE EQUIPE***************************/
    
    static Action AddPersToEqui()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();                 
                Set<Personne> p = i.getPersonnes();
                Set<Equipe> e = i.getEquipes();
                
                AffichPers(p);
                int idpers = fr.cdf.Utils.InOut.getInt("Id de la personne :");
                
                Personne p1 = null;
                for(Personne _p : p){
                    if(_p.getId() == idpers){
                        p1 = _p;
                        break;
                    }
                }
                
                if(p1 == null){
                    System.out.println("Personne invalide");
                }
                
                AffichEquipe(e);
                int idequip = fr.cdf.Utils.InOut.getInt("Id de l'equipe : ");
                Equipe e1 = null;
                for(Equipe _e : e)
                    if (_e.getId() == idequip){
                        e1 = _e;
                        break;
                    }
                
                if(e1 == null)
                    System.out.println("Erreur : Equipe inexistante");
                else if(p1 == null)
                    System.out.println("Erreur : Personne inexistante");
                else
                    if(e1.addBD(p1))
                        System.out.println("Succes");
                    else
                        System.out.println("Echec de l'ajout de la personne a l equipe");
            }
        };
    }

/**********************AJOUTER PERSONNE A UNE COMPETITION***************************/
    
    static Action AddPersToComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();                 
                Set<Personne> p = i.getPersonnes();
                Set<Competition> c = i.getCompetitions();
                
                AffichPers(p);
                int idpers = fr.cdf.Utils.InOut.getInt("Id de la personne :");
                
                Personne p1 = null;
                for(Personne _p : p){
                    if(_p.getId() == idpers){
                        p1 = _p;
                        break;
                    }
                }
                
                if(p1 == null){
                    System.out.println("Personne invalide");
                }
                
                AffichComp(c);
                int idcomp = fr.cdf.Utils.InOut.getInt("Id de la competition : ");
                Competition c1 = null;
                for(Competition _c : c)
                    if (_c.getId()== idcomp){
                        c1 = _c;
                        break;
                    }
                
                if(c1 == null)
                    System.out.println("Erreur : Competition inexistante");
                else if(p1 == null)
                    System.out.println("Erreur : Personne inexistante");
                else
                    if(!c1.estEnEquipe())
                    {
                        if(c1.addBD(p1))
                            System.out.println("Succes");
                        else
                            System.out.println("Echec de l'inscription de la personne a la Competition");
                    }
                    else
                        System.out.println("Echec de l'inscription : Cette competion se fait en Equipe");
            }
        };
    }
    
/**********************AJOUTER EQUIPE A UNE COMPETITION************************/
    
    static Action AddEquipeToComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();                 
                Set<Equipe> e = i.getEquipes();
                Set<Competition> c = i.getCompetitions();
                
                AffichEquipe(e);
                int idEquipe = fr.cdf.Utils.InOut.getInt("Id de l Equipe :");
                
                Equipe e1 = null;
                for(Equipe _e : e){
                    if(_e.getId() == idEquipe){
                        e1 = _e;
                        break;
                    }
                }
                
                if(e1 == null){
                    System.out.println("Equipe invalide");
                }
                
                AffichComp(c);
                int idcomp = fr.cdf.Utils.InOut.getInt("Id de la competition : ");
                Competition c1 = null;
                for(Competition _c : c)
                    if (_c.getId()== idcomp){
                        c1 = _c;
                        break;
                    }
                
                if(c1 == null)
                    System.out.println("Erreur : Competition inexistante");
                else if(e1 == null)
                    System.out.println("Erreur : Equipe inexistante");
                else
                   if(c1.estEnEquipe())
                   {   
                        if(c1.addBD(e1))
                            System.out.println("Succes");
                        else
                            System.out.println("Echec de l'inscription de l Equipe a la Competition");
                   }
                   else
                       System.out.println("Echec de l'inscription : Cette competition est individuel ");
            }
        };
    }
    
/****************************CREER UNE PERSONNE********************************/
    
    static Action newPersonne()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                String nom,prenom,mail;
                nom = fr.cdf.Utils.InOut.getString("Nom : ");
                prenom = fr.cdf.Utils.InOut.getString("Prenom : ");
                mail = fr.cdf.Utils.InOut.getString("mail : ");
                i.createPersonne(nom,prenom,mail);
                if(i.BDCreatePersonne(nom, prenom, mail))
                        System.out.println("Personne "+nom+" cree avec succes");
                    else
                        System.out.println("Erreur, Personne non cree");
            }
        };
    }
    
/****************************CREER UNE EQUIPE********************************/
    
    static Action newEquipe()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                String nom = fr.cdf.Utils.InOut.getString("Nom :");
                i.createEquipe(nom);
                if(i.BDCreateEquipe(nom))
                        System.out.println("Equipe "+nom+" cree avec succes");
                    else
                        System.out.println("Erreur, Equipe non cree");
            }
        };
    }

/****************************CREER UNE COMPETITION**********************************/  
    
    static Action newCompetition() //throws LocalDateException
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                boolean Team = true;
                boolean checkSaisie = false;
                LocalDate auj = LocalDate.now();
                String chx ;
                do
                {
                   chx = fr.cdf.Utils.InOut.getString(" 1 par Equpie \n 2 individuel:");
                   switch (chx) 
                   {
                        case "1":
                            Team=true;
                            checkSaisie = true;
                            break;
                        case "2":
                            Team=true;
                            checkSaisie = true;
                            break;
                        default:
                            System.out.println("Erreur de saisie");
                            checkSaisie = false;
                            break;
                    }
                }while(!checkSaisie);
                Inscriptions i = Inscriptions.getInscriptions();
                String nom = fr.cdf.Utils.InOut.getString("Nom :");
                LocalDate dateClo = LocalDate.now();
                boolean checkDate = false;
                do
                {
                    int jour = fr.cdf.Utils.InOut.getInt("Jour de la date de cloture des inscriptions : ");
                    int mois = fr.cdf.Utils.InOut.getInt("Mois(numero) de la date de cloture des inscriptions : ");
                    int annee = fr.cdf.Utils.InOut.getInt("Annee de la date de cloture des inscriptions : ");
                    try
                    {
                        dateClo = LocalDate.of(annee, mois, jour);
                        checkDate=true;
                    }
                    catch(DateTimeException e)
                    {
                        System.out.println("Erreur : date Inexistante !");
                    }
                }while(!checkDate);
                if(dateClo.isAfter(auj))
                {
                    i.createCompetition(nom,dateClo,Team);
                    if(i.BDCompetition(nom,dateClo,Team))
                        System.out.println("Competion "+nom+" cree avec succes");
                    else
                        System.out.println("Erreur, competition non cree");
                }
                else
                    System.out.println("Erreur de saisie, creation de la competition annule");   
            }
        };
    }
    
    
/******************************************************************************/
/********************************MENU SUPPRIMER********************************/
/******************************************************************************/    
    
    
    static Action ActionMenuSuprimer()
    {
        return new Action(){
            @Override
            public void optionSelectionnee() {
                Menu MenuS = new Menu("Suprimer : ");
                Option SupPers = new Option("Suprimer un candidat", "1",ActionMenuSupprCand());
                Option deInsPers = new Option("Deinscrire une Personne", "2",ActionMenuSupprPersFromComp());
                Option deInsEquipe = new Option("Deinscrire une Equipe", "3",ActionMenuSupprEquipeFromComp());
                Option supMbrEquip = new Option("Suipprimer un membre d'une equipe", "4",ActionMenuSupprPersFromteam());
                Option SupComp = new Option("Suprimer une competition", "5");
                MenuS.ajoute(SupPers);
                MenuS.ajoute(deInsPers);
                MenuS.ajoute(deInsEquipe);
                MenuS.ajoute(supMbrEquip);
                MenuS.ajoute(SupComp);
                MenuS.ajouteRevenir("r");
                MenuS.ajouteQuitter("q");
                MenuS.start();
            }
            
        };
    }
    
/*************************SUPPRIMER UN CANDIDAT********************************/    
   
    static Action ActionMenuSupprCand()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Personne> p = i.getPersonnes();
                Set<Equipe> e = i.getEquipes();
                System.out.println("Personnes : ");
                AffichPers(p);
                System.out.println("Equipes : ");
                AffichEquipe(e);
                int Id = fr.cdf.Utils.InOut.getInt("entrez l ID du candidat : ");
                Candidat c = i.createEquipe("SetCandName");
                c.supCand(Id);
                Menu MenuS = new Menu("Suprimer : ");
                MenuS.ajouteRevenir("r");
                MenuS.ajouteQuitter("q");
                MenuS.start();
            }
        };
    }
 
/************************DESINSCRIRE UNE PERSONNE******************************/    
   
    static Action ActionMenuSupprPersFromComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();                 
                Set<Personne> p = i.getPersonnes();
                Set<Competition> c = i.getCompetitions();
                
                AffichPers(p);
                int idpers = fr.cdf.Utils.InOut.getInt("Id de la personne :");
                
                Personne p1 = null;
                for(Personne _p : p){
                    if(_p.getId() == idpers){
                        p1 = _p;
                        break;
                    }
                }
                
                if(p1 == null){
                    System.out.println("Personne invalide");
                }
                
                Set<String> c2 = i.getinsCand(idpers);
                Competition comp = i.createCompetition("", LocalDate.MAX, true);
                Set<Integer> in = comp.getIdComp(idpers);
                Iterator iterP = c2.iterator();
                Iterator iterIn = in.iterator();
                
                if(in.isEmpty())
                {
                    System.out.println("Cette Personne n est inscrite a aucune competition. ");
                }
                else
                {
                    while(iterP.hasNext() && iterIn.hasNext())
                    {
                        System.out.println("ID : "+iterIn.next()+"  "+iterP.next());
                    }
                
                
                    int idcomp = fr.cdf.Utils.InOut.getInt("Id de la competition : ");
                    Competition c1 = null;
                    for(Competition _c : c)
                        if (_c.getId()== idcomp){
                            c1 = _c;
                            break;
                        }
                
                    if(c1 == null)
                        System.out.println("Erreur : Competition inexistante");
                    else if(p1 == null)
                        System.out.println("Erreur : Personne inexistante");
                    else
                        if(!c1.estEnEquipe())
                        {
                            if(c1.deInsCand(p1))
                                System.out.println("Succes");
                            else
                                System.out.println("Echec de la suppression de la personne a la Competition");
                        }
                            else
                                System.out.println("Echec de la suppression : Cette competion se fait en Equipe");
                }
            }
        };
    }
    
    /****************************DESINSCRIR EQUIPE*****************************/
    
    static Action ActionMenuSupprEquipeFromComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();                 
                Set<Equipe> e = i.getEquipes();
                Set<Competition> c = i.getCompetitions();
                
                AffichEquipe(e);
                int idEquipe = fr.cdf.Utils.InOut.getInt("Id de l Equipe :");
                
                Equipe e1 = null;
                for(Equipe _e : e){
                    if(_e.getId() == idEquipe){
                        e1 = _e;
                        break;
                    }
                }
                
                if(e1 == null){
                    System.out.println("Equipe invalide");
                }
                
                
                Set<String> c2 = i.getinsCand(idEquipe);
                Competition comp = i.createCompetition("", LocalDate.MAX, true);
                Set<Integer> in = comp.getIdComp(idEquipe);
                Iterator iterP = c2.iterator();
                Iterator iterIn = in.iterator();
                if(in.isEmpty())
                {
                    System.out.println("Cette equipe n est inscrite a aucune competition. ");
                }
                else
                {
                    while(iterP.hasNext() && iterIn.hasNext())
                    {
                        System.out.println("ID : "+iterIn.next()+"  "+iterP.next());
                    }
                
                
                    int idcomp = fr.cdf.Utils.InOut.getInt("Id de la competition : ");
                    Competition c1 = null;
                    for(Competition _c : c)
                        if (_c.getId()== idcomp){
                            c1 = _c;
                            break;
                        }
                
                    if(c1 == null)
                        System.out.println("Erreur : Competition inexistante");
                    else if(e1 == null)
                        System.out.println("Erreur : Equipe inexistante");
                    else
                        if(c1.estEnEquipe())
                        {   
                            if(c1.deInsCand(e1))
                                System.out.println("Succes");
                            else
                                System.out.println("Echec de la suppression de l Equipe a la Competition");
                        }
                        else
                            System.out.println("Echec de la suppression : Cette competition est individuel ");
                }
            }
        };
    }
    
    /********************ENLEVER PERSONNE FROM EQUIPE**************************/    
   
    static Action ActionMenuSupprPersFromteam()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();                 
                Set<Personne> p = i.getPersonnes();
                Set<Equipe> e = i.getEquipes();
                
                AffichPers(p);
                int idpers = fr.cdf.Utils.InOut.getInt("Id de la personne :");
                
                Personne p1 = null;
                for(Personne _p : p){
                    if(_p.getId() == idpers){
                        p1 = _p;
                        break;
                    }
                }
                
                if(p1 == null){
                    System.out.println("Personne invalide");
                }
                
                Equipe eq = i.createEquipe("");
                Set<String> c2 = eq.getNomMbr(idpers);
                Set<Integer> in = eq.getIdEqui(idpers);
                Iterator iterP = c2.iterator();
                Iterator iterIn = in.iterator();
                if(in.isEmpty())
                {
                    System.out.println("Cette Personne n appartient a aucune Equipe . ");
                }
                else
                {
                    while(iterP.hasNext() && iterIn.hasNext())
                    {
                        System.out.println("ID : "+iterIn.next()+"  "+iterP.next());
                    }
                    int idequip = fr.cdf.Utils.InOut.getInt("Id de l'equipe : ");
                    Equipe e1 = null;
                    for(Equipe _e : e)
                        if (_e.getId() == idequip){
                            e1 = _e;
                            break;
                        }
                
                    if(e1 == null)
                        System.out.println("Erreur : Equipe inexistante");
                    else if(p1 == null)
                        System.out.println("Erreur : Personne inexistante");
                    else
                        if(e1.supPers(p1))
                            System.out.println("Succes");
                        else
                            System.out.println("Echec de la suppression de la personne de l equipe");
                }
            }
        };
    }
    
/******************************************************************************/
/********************************MENU MODIFIER*********************************/
/******************************************************************************/  
    
    
    static Action ActionMenuModifier(){
        return new Action(){
            @Override
            public void optionSelectionnee() {
                Menu MenuM = new Menu("Modifier : ");
                
                Option chNomCand = new Option("Modifier le nom de l equipe ou le nom de famille d un candidat","1",ActionMenuSetCand());
                Option ChPrenomPers = new Option("Modifier le prenom d une personne","2",ActionMenuSetCandPrenom());
                Option ChMailPers = new Option("Modifier le mail d une personne","3",ActionMenuSetMailPers());
                Option ChDateCloComp = new Option("Modifier la date de cloture d une competition","4",ActionMenuSetDateCloComp());
                Option ChNomComp = new Option("Modifier le nom d une competition","5",ActionMenuSetNomComp());             
                MenuM.ajoute(chNomCand);
                MenuM.ajoute(ChPrenomPers);
                MenuM.ajoute(ChMailPers);
                MenuM.ajoute(ChDateCloComp);
                MenuM.ajoute(ChNomComp);                
                MenuM.ajouteRevenir("r");
                MenuM.ajouteQuitter("q");
                MenuM.start();
            }
        };
    }

/*************************CHANGER NOM CANDIDAT********************************/    
   
    static Action ActionMenuSetCand()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Personne> p = i.getPersonnes();
                Set<Equipe> e = i.getEquipes();
                System.out.println("Personnes : ");
                AffichPers(p);
                System.out.println("Equipes : ");
                AffichEquipe(e);
                int Id = fr.cdf.Utils.InOut.getInt("entrez l ID du candidat : ");
                String name = fr.cdf.Utils.InOut.getString("Entrez le nouveau nom : ");
                Candidat c = i.createEquipe("SetCandName");
                c.modifNom(Id, name);
                System.out.println("Succes");
                Menu rechCand = new Menu("");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start(); 
            }
        };
    }

/*************************CHANGER PRENOM CANDIDAT********************************/    
   
    static Action ActionMenuSetCandPrenom()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Set<Personne> p = i.getPersonnes();
                System.out.println("Personnes : ");
                AffichPers(p);
                int Id = fr.cdf.Utils.InOut.getInt("entrez l ID du candidat : ");
                String name = fr.cdf.Utils.InOut.getString("Entrez le nouveau nom : ");
                Personne c = i.createPersonne("SetCandName","","");
                c.modifPrenom(Id, name);
                System.out.println("Succes");
                Menu rechCand = new Menu("");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start(); 
            }
        };
    }
      
/********************CHANGER DATE CLOTURE COMPETITION**************************/    
   
    static Action ActionMenuSetDateCloComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions(); 
                Set<Competition> comp = i.getCompetitions();
                Competition c = i.createCompetition("Modif Date", LocalDate.MAX, true);
                AffichComp(comp);
                int id = fr.cdf.Utils.InOut.getInt("ID de la Competition : ");
                int jour = fr.cdf.Utils.InOut.getInt("Jour de la date de cloture des inscriptions : ");
                int mois = fr.cdf.Utils.InOut.getInt("Mois(numero) de la date de cloture des inscriptions : ");
                int annee = fr.cdf.Utils.InOut.getInt("Annee de la date de cloture des inscriptions : ");
                LocalDate dateClo = LocalDate.MAX;
                boolean checkDate = false;
                do
                {
                    try
                    {
                       dateClo = LocalDate.of(annee, mois, jour);
                       checkDate = true;
                    }
                    catch(DateTimeException e)
                    {
                        System.out.println("Erreur : Date invalide ");
                    }
                }while(!checkDate);
                c.modifDateCloture(id, dateClo);
                Menu rechCand = new Menu("");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();
            }
        };
    }
    
/************************CHANGER MAIL PERSONNE*******************************/    
   
    static Action ActionMenuSetMailPers()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions(); 
                Set<Personne> p = i.getPersonnes();
                AffichPers(p);
                Personne p1 = i.createPersonne("", "", "");
                int id = fr.cdf.Utils.InOut.getInt("ID de la personne : ");
                String mail = fr.cdf.Utils.InOut.getString("Nouveau Mail : ");
                p1.modifMail(id, mail);
                Menu rechCand = new Menu("Recherche de Personnes : ");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();  
                
            }
        };
    }
    
/************************CHANGER NOM COMPETITION*******************************/    
   
    static Action ActionMenuSetNomComp()
    {
        return new Action()
        {
            @Override
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions(); 
                Set<Competition> comp = i.getCompetitions();
                Competition c = i.createCompetition("Modif Date", LocalDate.MAX, true);
                AffichComp(comp);
                int id = fr.cdf.Utils.InOut.getInt("ID de la Competition : ");
                String name = fr.cdf.Utils.InOut.getString("Nouveau Nom :");
                c.modifNom(id, name);
                Menu rechCand = new Menu("");
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();
            }
        };
    }
    
/********************************************************************/
/*                         MENU PRINCIPAL                           */
/********************************************************************/
    
    static Menu MenuP()
    {
        Menu menuPrincipal = new Menu("Menu Principal");
        Option rechercher = new Option("rechercher","1",ActionMenuRecherche());
        Option inscrire = new Option("Inscriptions","2",ActionMenuInscription());
        Option suppr = new Option("Supprimer","3",ActionMenuSuprimer());
        Option modif = new Option("Modifier","4",ActionMenuModifier());
        menuPrincipal.ajoute(rechercher);
        menuPrincipal.ajoute(inscrire);
        menuPrincipal.ajoute(suppr);
        menuPrincipal.ajoute(modif);
        menuPrincipal.ajouteQuitter("q");
        return menuPrincipal;
    }
    public static void main(String[] args)
    {
        Menu m = MenuP();
        m.start();
    }
}
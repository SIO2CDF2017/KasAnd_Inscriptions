package fr.cdf;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import utilitaires.ligneDeCommande.*;
/**
 *
 * @author vkasperski
 */
public class InterfaceUser {

    static void AffichEqupie(Set<Equipe> e)
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
                Option SearchGetInsc = new Option("Recherche d'inscriptions","5");
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
                AffichEqupie(e);
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
                AffichEqupie(e);
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
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();
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
                Option addPersToComp = new Option("Inscrire une personne existante à une competition existante","5");
                Option addEquiToComp = new Option("Inscrire une équipe existante à une competion existante","6");
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
                int idpers = utilitaires.EntreesSorties.getInt("Id de la personne :");
                
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
                
                AffichEqupie(e);
                int idequip = utilitaires.EntreesSorties.getInt("Id de l'equipe : ");
                Equipe e1 = null;
                for(Equipe _e : e)
                    if (_e.getId() == idequip){
                        e1 = _e;
                        break;
                    }
                
                if(e1 == null)
                    System.out.println("Equipe nulle");
                else if(p1 == null)
                    System.out.println("Candidat null");
                else
                    if(e1.addBD(p1))
                        System.out.println("Succes");
                    else
                        System.out.println("Echec de l'ajout de la personne a l equipe");
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
                nom = utilitaires.EntreesSorties.getString("Nom : ");
                prenom = utilitaires.EntreesSorties.getString("Prenom : ");
                mail = utilitaires.EntreesSorties.getString("mail : ");
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
                String nom = utilitaires.EntreesSorties.getString("Nom :");
                i.createEquipe(nom);
                if(i.BDCreateEquipe(nom))
                        System.out.println("Equipe "+nom+" cree avec succes");
                    else
                        System.out.println("Erreur, Equipe non cree");
            }
        };
    }

/****************************CREER UNE EQUIPE**********************************/    
    static Action newCompetition()
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
                   chx = utilitaires.EntreesSorties.getString(" 1 par Equpie \n 2 individuel:");
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
                String nom = utilitaires.EntreesSorties.getString("Nom :");
                int jour = utilitaires.EntreesSorties.getInt("Jour de la date de cloture des inscriptions : ");
                int mois = utilitaires.EntreesSorties.getInt("Mois(numero) de la date de cloture des inscriptions : ");
                int annee = utilitaires.EntreesSorties.getInt("Annee de la date de cloture des inscriptions : ");
                LocalDate dateClo = LocalDate.of(annee, mois, jour);
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
    
    
    static Action ActionMenuSuprimer(){
        return new Action(){
            @Override
            public void optionSelectionnee() {
                Menu MenuS = new Menu("Suprimer : ");
                Option SupPers = new Option("Suprimer un candidat", "1");
                Option deInsCand = new Option("Deinscrire un candidat", "2");
                Option supMbrEquip = new Option("Suimer un membre d'une equipe", "3");
                Option SupComp = new Option("Suprimer une competition", "4");
                MenuS.ajoute(SupPers);
                MenuS.ajoute(deInsCand);
                MenuS.ajoute(supMbrEquip);
                MenuS.ajoute(SupComp);
                MenuS.ajouteRevenir("r");
                MenuS.ajouteQuitter("q");
                MenuS.start();
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
                
                Option chNomCand = new Option("Modifier le nom de l equipe ou le nom de famille d un candidat","1");
                Option ChPrenomPers = new Option("Modifier le prenom d une personne","2");
                Option ChMailPers = new Option("Modifier le mail d une personne","3");
                Option ChDateCloComp = new Option("Modifier la date de cloture d une competition","4");
                Option ChNomComp = new Option("Modifier le nom d une competition","5");             
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
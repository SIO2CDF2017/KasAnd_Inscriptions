package fr.cdf;

import java.time.LocalDate;
import utilitaires.ligneDeCommande.*;
/**
 *
 * @author vkasperski
 */
public class InterfaceUser {
    
    static Action ActionMenuRechercheCand()
    {
        return new Action()
        {
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                Menu rechCand = new Menu("Recherche de Candidats :");
                
                rechCand.ajouteRevenir("r");
                rechCand.ajouteQuitter("q");
                rechCand.start();                
            }
        };
    }
    
    
    
    static Action ActionMenuRecherche()
    {
        return new Action()
        {
            public void optionSelectionnee()
            {
                Menu mr = new Menu("Rechercher :");
                Option SearchGetCand = new Option("Recherche de Candidats(équipes et personnes)","1");
                Option SearchGetEqui = new Option("Recherche d'équipes","2");
                Option SearchGetPers = new Option("Recherche de personnes","3");
                Option SearchGetComp = new Option("Recherche de Ccompetitions","4");
                Option SearchGetInsc = new Option("Recherche d'inscriptions","5");
                mr.ajoute(SearchGetCand);
                mr.ajoute(SearchGetEqui);
                mr.ajoute(SearchGetPers);
                mr.ajoute(SearchGetComp);
                mr.ajoute(SearchGetInsc);
                mr.ajouteRevenir("r");;
                mr.ajouteQuitter("q");
                mr.start();
                
            }
	};
    }

    static Action ActionMenuInscription()
    {
        return new Action()
        {
            public void optionSelectionnee()
            {
                Menu insc = new Menu("Inscriptions :");
                Option creatPers = new Option("nouvelle Personne","1",newPersonne());
                Option creatEqui = new Option("nouvelle Equipe","2",newEquipe());
                Option creatComp = new Option("nouvelle Competition","3",newCompetition());
                Option addPersToEqui = new Option("Ajouter une personne existante à une Equipe existante","4");
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
    
    static Action newPersonne()
    {
        return new Action()
        {
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                String nom,prenom,mail;
                nom = utilitaires.EntreesSorties.getString("Nom : ");
                prenom = utilitaires.EntreesSorties.getString("Prenom : ");
                mail = utilitaires.EntreesSorties.getString("mail : ");
                i.createPersonne(nom,prenom,mail);
                
            }
        };
    }
    
    static Action newEquipe()
    {
        return new Action()
        {
            public void optionSelectionnee()
            {
                Inscriptions i = Inscriptions.getInscriptions();
                String nom;
                nom = utilitaires.EntreesSorties.getString("Nom :");
                i.createEquipe(nom);
            }
        };
    }
    
    static Action newCompetition()
    {
        return new Action()
        {
            @SuppressWarnings("StringEquality")
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
                    i.createCompetition(nom,dateClo,Team);
                else
                    System.out.println("Erreur de saisie, creation de la competition annule");   
            }
        };
    }
    static Menu MenuP()
    {
        Menu menuPrincipal = new Menu("Menu Principal");
        Option rechercher = new Option("rechercher","1",ActionMenuRecherche());
        Option inscrire = new Option("Inscriptions","2",ActionMenuInscription());
        Option suppr = new Option("Supprimer","3");
        Option modif = new Option("Modifier","4");
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

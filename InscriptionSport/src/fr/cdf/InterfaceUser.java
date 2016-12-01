package fr.cdf;
import utilitaires.ligneDeCommande.*;
/**
 *
 * @author vkasperski
 */
public class InterfaceUser {
    static Action ActionMenuRecherche()
    {
        return new Action()
        {
            public void optionSelectionnee()
            {
                Menu mr = new Menu("Rechercher :");
                Option candGetCand = new Option("Recherche de Candidat","1");
                Option candGetCand = new Option("Recherche de Candidat","1");
                mr.start();
                
            }
	};
    }

    static Menu MenuP()
    {
        Menu menuPrincipal = new Menu("Menu Principal");
        Option rechercher = new Option("rechercher","1",ActionMenuRecherche());
        Option inscrire = new Option("Inscriptions","2");
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

package fr.cdf;
import utilitaires.ligneDeCommande.*;
/**
 *
 * @author vkasperski
 */
public class InterfaceUser {
    static Option getRecherche()
    {
        Option rechercher = new Option("rechercher","1");
        return rechercher;
    }
    
    static Option getInscription()
    {
        Option inscrir = new Option("Inscription","2");
        return inscrir;
    }
    
    static Option getSuppr()
    {
        Option suppr = new Option("Supprimer","3");
        return suppr;
    }
    
    static Option getModif()
    {
        Option modif = new Option("Modifier","4");
        return modif;
    }
    
    static Menu MenuP()
    {
        Menu menuPrincipal = new Menu("Menu Principal");
        menuPrincipal.ajoute(getRecherche());
        menuPrincipal.ajoute(getInscription());
        menuPrincipal.ajoute(getSuppr());
        menuPrincipal.ajoute(getModif());
        menuPrincipal.ajouteQuitter("q");
        return menuPrincipal;
    }
    
}

package xyz.teamkasand.Interface;

import java.util.ArrayList;
import java.util.List;
import xyz.teamkasand.Utils.CommandsLigne.Action;
import xyz.teamkasand.Utils.CommandsLigne.ActionListe;
import xyz.teamkasand.Utils.CommandsLigne.Liste;
import xyz.teamkasand.Utils.CommandsLigne.Menu;
import xyz.teamkasand.Utils.CommandsLigne.Option;



/**
 *
 * @author vkasperski
 */
public class NewInterfaceUser {
    static Action ActionMenuSearchPers(){
        final ArrayList<String> personnes = new ArrayList<>();
        personnes.add("Ginette");
	personnes.add("Marcel");
	personnes.add("Gisèle");
        Liste<String> menu = new Liste<String>("Liste des Personnes", 
				new ActionListe<String>()
		{
			// Retourne la liste des personnes formant le menu
			public List<String> getListe()
			{
                            return personnes;
			}

			// Exécutée automatiquement lorsqu'un élément de liste est sélectionné
			public void elementSelectionne(int indice, String element)
			{
				System.out.println("Vous avez sélectionné "+ element+ ", qui a l'indice " + indice);
			}
		});
        return null;
    }
    static Menu menuPrincipal()
    {
        Menu MenuP = new Menu("Menu Principal");
        Option searchPers = new Option("Rechercher une personne","1",ActionMenuSearchPers());
        //Option searchTeam = new Option("Rechercher une Equipe","2",ActionMenuSearchTeam());
        //Option searchComp = new Option("Rechercher une Competition","3",ActionMenuSearchComp());
        MenuP.ajoute(searchPers);
        MenuP.ajouteQuitter("q");
        return MenuP;
    }
    public static void main(String []args){
        
        Menu m = menuPrincipal();
        m.start();
    }
}

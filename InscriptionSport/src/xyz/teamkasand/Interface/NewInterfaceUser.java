package xyz.teamkasand.Interface;

import xyz.teamkasand.Utils.CommandsLigne.Menu;



/**
 *
 * @author vkasperski
 */
public class NewInterfaceUser {
    static Menu menuPrincipal()
    {
        Menu MenuP = new Menu("Menu Principal");
        MenuP.ajouteQuitter("q");
        return MenuP;
    }
    public static void main(String []args){
        
        Menu m = menuPrincipal();
        m.start();
    }
}

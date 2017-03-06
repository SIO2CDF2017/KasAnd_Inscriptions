/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class PersonneDB {
    private static Inscriptions inscriptions;
    private static final String MYSQL_URL = "jdbc:mysql://localhost/inscription";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PSW = "";
    
    public ArrayList<Personne> getPersonnes(){
                MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
        Inscriptions i = Inscriptions.getInscriptions(); 
        ArrayList<Personne> personnes = null;
        try {
            ms.connect();
            ResultSet rs = ms.execSelect("call getPers()");
            while (rs.next()) {
                Personne p = i.createPersonne(rs.getNString("nom"), rs.getNString("prenom"), rs.getNString("mail"), rs.getInt("ID"));
                personnes.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ms.close();
        
        return personnes;
    }
    
    public boolean CreatePersonne(String nom, String prenom, String mail){
        MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
                    //BDD
        if (ms.connect()) {
            try {
            ResultSet  rs = ms.execSelect("SELECT * FROM CANDIDAT FROM `candidat` WHERE CANDIDAT.Nom = \""+nom+"\"");
            if (rs.next()) {
                return false;
            }else{
                ms.exec("call creatPers('"+nom+",'"+prenom+"','"+mail+"')");
                return true;
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return false;
        }
        ms.close();
        return false;
    }
}

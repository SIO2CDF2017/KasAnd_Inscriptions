
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.data;

import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author asandolo
 */
public class EquipeDB {
    private static Inscriptions inscriptions;
    private static final String MYSQL_URL = "jdbc:mysql://localhost/inscription";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PSW = "";
    
    public ArrayList<Equipe> getEquipes(){
        MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
        Inscriptions i = Inscriptions.getInscriptions(); 
        ArrayList<Equipe> equipes = null;
        try {
            ms.connect();
            ResultSet rs = ms.execSelect("call getEquipe()");
            while (rs.next()) {
                Equipe e = i.createEquipe(rs.getNString("nom"), rs.getInt("ID"));
                equipes.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ms.close();
        
        return equipes;
    } 
    
    public ArrayList<Personne> getMembres(){
        MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
        ArrayList<Personne> membres = null;
        try {
            ms.connect();
            
            ResultSet rs = ms.execSelect("call RetourPersonnEquipe()");
            while (rs.next()) {                
                Inscriptions i = Inscriptions.getInscriptions();
                Personne p = i.createPersonne(rs.getNString("Nom"), rs.getNString("Prenom"), rs.getNString("Mail"), rs.getInt("IdCandidat"));
                membres.add(p);
            }
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        ms.close();
        return membres;
    }
   
    public boolean createEquipe(String nom){
        MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
                            //BDD
                if (ms.connect()) {
                    try {
                    ResultSet  rs = ms.execSelect("SELECT * FROM CANDIDAT FROM `candidat` WHERE `idCandidat` NOT IN (SELECT `IdCandidat` FROM `personne`) AND CANDIDAT.Nom = \""+nom+"\"");
                    if (rs.next()) {
                        return false;
                    }else{
                        ms.exec("call creatEquipe('"+nom+"')");
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

package xyz.teamkasand;

import xyz.teamkasand.data.MySQL;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Candidat à un événement sportif, soit une personne physique, soit une équipe.
 *
 */

public abstract class Candidat implements Comparable<Candidat>, Serializable
{
    private static final long serialVersionUID = -6035399822298694746L;
    private Inscriptions inscriptions;
    private String nom;
    private Set<Competition> competitions;
    private String MYSQL_URL;
    private String MYSQL_USER;
    private String MYSQL_PSW;
    private int id;
    
    Candidat(Inscriptions inscriptions, String nom)
    {
        this(inscriptions,nom,-1);
    }
    
    
    Candidat(Inscriptions inscriptions, String nom,int id)
    {
        this.inscriptions = inscriptions;
        this.nom = nom;
        competitions = new TreeSet<>();
        this.id = id;
    }
    
    /**
     * Retourne le nom du candidat.
     * @return
     */
    
    public String getNom()
    {
        return nom;
    }
    
    /**
     * Modifie le nom du candidat.
     * @param nom
     */
    public boolean modifNom(int id, String name){
        if (MySQL.isConnect()) {
            try {
                
                MySQL.execUpdate("call RENAMECANDIDAT("+id+",'"+name+"');");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    /**
     * Retourne toutes les compétitions auxquelles ce candidat est inscrit.s
     * @return
     */
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
    
    
    public Set<String> getCompetCandidat(int id){
        Set<String> c = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                MySQL.connect();
                ResultSet rs = MySQL.execSelect("call COMPETCAMDIDAT("+id+")");
                while (rs.next()) {
                    c.add(rs.getNString("Epreuve"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        
        return c;
    }
    
    public Set<Competition> getCompetitions()
    {
        return Collections.unmodifiableSet(competitions);
    }
    
    boolean add(Competition competition)
    {
        return competitions.add(competition);
    }
    
    boolean remove(Competition competition)
    {
        return competitions.remove(competition);
    }
    
    /**
     * Supprime un candidat de l'application.
     */
    
    public void supCand(int id){
        if(MySQL.isConnect()){
            try {
                
                MySQL.exec("call DELETECANDIDAT("+id+")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void delete()
    {
        for (Competition c : competitions)
            c.remove(this);
        inscriptions.remove(this);
    }
    
    @Override
    public int compareTo(Candidat o)
    {
        return getNom().compareTo(o.getNom());
    }
    
    @Override
    public String toString()
    {
        return "\n" + getNom() /* + " -> inscrit à " + getCompetitions()*/;
    }
}

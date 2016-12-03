package fr.cdf;

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
        private static final String MYSQL_URL = "jdbc:mysql://localhost/inscription";
        private static final String MYSQL_USER = "root";
        private static final String MYSQL_PSW = "";
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
	public void modifNom(int id, String name){
           MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            try {
                ms.connect();
                
                ms.execUpdate("call RENAMECANDIDAT("+id+",'"+name+"');");
            } catch (Exception e) {
                e.printStackTrace();
            }
           ms.close();
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
            
            MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            Set<String> c = new LinkedHashSet<>();
            try {
                ms.connect();
                ResultSet rs = ms.execSelect("call COMPETCAMDIDAT("+id+")");
                while (rs.next()) {                    
                    c.add(rs.getNString("Epreuve"));
                }
            } catch (Exception e) {
                e.printStackTrace();
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
		return "\n" + getNom() + " -> inscrit à " + getCompetitions();
	}
}

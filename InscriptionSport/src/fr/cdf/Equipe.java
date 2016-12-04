package fr.cdf;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */


public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
        private static final String MYSQL_URL = "jdbc:mysql://localhost/inscription";
        private static final String MYSQL_USER = "root";
        private static final String MYSQL_PSW = "";
	
	Equipe(Inscriptions inscriptions, String nom)
	{
		this(inscriptions, nom,-1);
	}
        
        Equipe(Inscriptions inscriptions, String nom, int id)
	{
		super(inscriptions, nom,id);
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
     * @param id
     * @return 
	 */
        
        public Set<Integer> getIdEqui(int id){
             MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
             Set<Integer> ids = new LinkedHashSet<>();
             try {
                ms.connect();
                
                ResultSet rs = ms.execSelect("SELECT IdCandidatEquipe As id FROM APPARTENIR WHERE IdCandidatPersonne = "+id+" ");
                 while (rs.next()) {                     
                     ids.add(rs.getInt("id"));
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }
             ms.close();
             return Collections.unmodifiableSet(ids);
        }
	
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */
        
        public boolean addBD(Personne membre)
	{
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Personne p = membre;
            
            if (ms.connect()) {
                try {
                    ResultSet rs = ms.execSelect("SELECT * FROM APPARTENIR WHERE IdCandidatPersonne = "+p.getId()+" AND IdCandidatEquipe = "+this.getId()+"");
                    if (rs.next()) {
                        return false;
                    }else{
                       ms.exec("call ajoutPers("+p.getId()+","+this.getId()+")");
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
        
	public boolean add(Personne membre)
	{
		membre.add(this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
        public boolean supPers(Personne membre){
            MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            Personne p = membre;
            
            if (ms.connect()) {
                try {
                       ms.exec("call supprMembrEq("+p.getId()+","+this.getId()+")");
                       return true;                 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                return false;
            }
            ms.close();
            return false;
        }
        
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	@Override
	public void delete()
	{
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}

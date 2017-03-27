package xyz.teamkasand;

import xyz.teamkasand.data.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import xyz.teamkasand.config.config;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */


public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
        private String MYSQL_URL;
        private String MYSQL_USER;
        private String MYSQL_PSW;
        private Inscriptions inscriptions = new Inscriptions();
	private config c = new config();
        private final HashMap<String, Object> conf = c.getConfigMysql();
        
        Equipe(String nom)
        {
            this(nom, -1);
        }
        
        Equipe(String nom, int id)
        {
            this(new Inscriptions(), nom, id);
        }
        
	Equipe(Inscriptions inscriptions, String nom)
	{
            this(inscriptions, nom,-1);
	}
        
        Equipe(Inscriptions inscriptions, String nom, int id)
	{
		super(inscriptions, nom,id);
                this.MYSQL_URL = (String) conf.get("url");
                this.MYSQL_USER = (String) conf.get("user");
                this.MYSQL_PSW = (String) conf.get("pass");
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
                
                ResultSet rs = ms.execSelect("SELECT IdCandidatEquipe As id FROM appartenir WHERE IdCandidatPersonne = "+id+" ");
                 while (rs.next()) {                     
                     ids.add(rs.getInt("id"));
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }
             ms.close();
             return Collections.unmodifiableSet(ids);
        }
        
        public Set<String> getNomMbr(int id){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Set<String> noms = new LinkedHashSet<>();
            try {
                ms.connect();
                
                ResultSet rs = ms.execSelect("SELECT Nom FROM CANDIDAT, appartenir WHERE CANDIDAT.IdCandidat = appartenir.IdCandidatEquipe AND CANDIDAT.IdCandidat = appartenir.IdCandidatEquipe AND appartenir.IdCandidatPersonne = "+id+"");
                while (rs.next()) {                    
                    noms.add(rs.getNString("Nom"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ms.close();
            return Collections.unmodifiableSet(noms);
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
            
            if (ms.connect()) {
                try {
                    ResultSet rs = ms.execSelect("SELECT * FROM appartenir WHERE IdCandidatPersonne = "+membre.getId()+" AND IdCandidatEquipe = "+this.getId()+"");
                    if (rs.next()) {
                        return false;
                    }else{
                       ms.exec("call ajoutPers("+membre.getId()+","+this.getId()+")");
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
        
        public boolean addBD(int membreID)
	{
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            
            if (ms.connect()) {
                try {
                    ResultSet rs = ms.execSelect("SELECT * FROM appartenir WHERE IdCandidatPersonne = "+membreID+" AND IdCandidatEquipe = "+this.getId()+"");
                    if (rs.next()) {
                        return false;
                    }else{
                       ms.exec("call ajoutPers("+membreID+","+this.getId()+")");
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
        
        public ArrayList<Personne> getMembresEquipe(int id)
	{       MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
		ArrayList<Personne> personnes = new ArrayList<>();
                try {
                ms.connect();
                
                    ResultSet r = ms.execSelect("call RetourPersonnEquipe("+id+")");
                    while (r.next()) {
                        Personne p = inscriptions.createPersonne(
                                r.getNString("Nom"),
                                r.getNString("Prenom"),
                                r.getNString("Mail"),
                                r.getInt("IdCandidat"));
                        if(p != null){
                          personnes.add(p);  
                        }
                        
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
                ms.close();
		return personnes;
	}
}
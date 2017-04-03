package xyz.teamkasand;

import xyz.teamkasand.data.MySQL;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */

public class Competition implements Comparable<Competition>, Serializable
{
	private static final long serialVersionUID = -2882150118573759729L;
	private Inscriptions inscriptions;
	private String nom;
	private Set<Candidat> candidats;
	private LocalDate dateCloture;
	private boolean enEquipe = false;
        private LocalDate ajd = LocalDate.now();
        private int id;
        private static final String MYSQL_URL = "jdbc:mysql://217.182.50.221/inscription";
        private static final String MYSQL_USER = "ins";
        private static final String MYSQL_PSW = "yolo";

        
        Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe)
	{
		this(inscriptions,nom,dateCloture,enEquipe,-1);
	}
        
	Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe, int id)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
                this.id = id;
	}
	
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	
        public int getId(){
            return this.id;
        }
        
        public void setId(int id){
            this.id = id;
        }
        
        public Set<Integer> getIdComp(int id){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Set<Integer> ids = new LinkedHashSet<>();
            try {
                ms.connect();
                
                ResultSet rs = ms.execSelect("SELECT inscrire.IdCompetition As id FROM INSCRIRE WHERE IdCandidat = "+id+"");
                while (rs.next()) {                    
                    ids.add(rs.getInt("id"));
                }
            } catch (Exception e) {
            }
            ms.close();
            return Collections.unmodifiableSet(ids);
        }
        
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
        
       public void modifNom(int id, String name){
            MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            try {
                ms.connect();
                
                ms.execUpdate("call modifnomcompetition("+id+",'"+name+"')");
                        
            } catch (Exception e) {
                e.printStackTrace();
            }
            ms.close();
        }
	
	public void setNom(String nom)
	{
		this.nom = nom ;
	}
	
	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes.
	 * @return
	 */
	
	public boolean inscriptionsOuvertes()
	{
		// retourner vrai si et seulement si la date système est antérieure à la date de clôture.    
            
            if (ajd.isBefore(this.dateCloture)) {
                return true;
            }
            return false;
	}
	
	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */
        
        public boolean isopen(int id){
            LocalDate dc = this.dateClotureInscriptions(id);
            LocalDate ajd = LocalDate.now();
            
            if (ajd.isBefore(dc))
                return true;
            else
                return false;
            
        }
        
	public LocalDate dateClotureInscriptions(int id){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
             LocalDate dci = LocalDate.MIN;
            if (ms.connect()) {
                try {
                    ResultSet rs = ms.execSelect("call dateClotureInscriptions("+id+");");
                    if(rs.next())
                        dci = rs.getDate("Date_Cloture").toLocalDate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }  

            return dci;
        }
        
        
	public LocalDate getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */
	
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */
	public boolean modifDateCloture(int id, LocalDate DateC){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
             if (DateC.isBefore(this.dateCloture)){ 
                 return false; 
             }else{
                 if(ms.connect()){
                     try {
                         ms.execUpdate("call modifdatecloture("+id+",'"+DateC+"')");
                         return true;
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }else{
                     return false;
                 }
                return true;
            }
        }
        
	public void setDateCloture(LocalDate DateC)
	{
		//  vérifier que l'on avance pas la date.
                
            if (DateC.isAfter(this.dateCloture) || DateC.isBefore(ajd)) {}else{
                    this.dateCloture = DateC;
            }
	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	public Set<String> getCandidatInscrit(int id){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Set<String> names = new LinkedHashSet<>();
            try {
                ms.connect();
                ResultSet rs = ms.execSelect("call candidatsInscrits("+id+")");
                while (rs.next()) {                    
                    names.add(rs.getNString("nom"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ms.close();
            return Collections.unmodifiableSet(names);
        }
        
        public ArrayList<Equipe> getEquipesInscrit(int id){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            ArrayList<Equipe> names = new ArrayList<Equipe>();
            try {
                ms.connect();
                ResultSet rs = ms.execSelect("call candidatsInscrits("+id+")");
                while (rs.next()) {
                    Equipe c = inscriptions.createEquipe(rs.getNString("nom"),rs.getInt("idCandidat"));
                    names.add(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ms.close();
            return names;
        }
        
        public ArrayList<Personne> getPersonneInscrit(int id){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            ArrayList<Personne> p = null; 
            return p;
        }
        
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * inscriptions sont closes.
	 * @param personne
	 * @return
	 */
	public boolean addBD(Personne personne){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Personne p = personne;
            
            if (ms.connect()) {
                try {
                    ResultSet rs = ms.execSelect("SELECT * FROM inscrire WHERE IdCandidat = "+p.getId()+" AND IdCompetition = "+this.getId()+"");
                    if (rs.next()) {
                        return false;
                    }else{
                       ms.exec("call ajouterPersonneACompetition("+p.getId()+","+this.getId()+")");
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
 
        
	public boolean addBD(Equipe equipe){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Equipe e = equipe;
            
            if (ms.connect()) {
                try {
                    ResultSet rs = ms.execSelect("SELECT * FROM inscrire WHERE IdCandidat = "+e.getId()+" AND IdCompetition = "+this.getId()+"");
                    if (rs.next()) {
                        return false;
                    }else{
                       ms.exec("call ajouterEquipeACompetition("+e.getId()+","+this.getId()+")");
                       return true;
                    }                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                return false;
            }
            ms.close();
            return false;            
        }
        
	public boolean add(Personne personne)
	{
            	//  vérifier que la date de clôture n'est pas passée
            
            if (ajd.isBefore(this.dateCloture)) {
                if (enEquipe)
			throw new RuntimeException();
		personne.add(this);
		return candidats.add(personne);  
            }
            return false;
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes.
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		//  vérifier que la date de clôture n'est pas passée
            if (ajd.isBefore(this.dateCloture)) {
                if (!enEquipe)
			throw new RuntimeException();
		equipe.add(this);
		return candidats.add(equipe);                
            }
            return false;

	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
        public boolean deInsCand(Equipe equipe){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Equipe e = equipe;
            
            if (ms.connect()) {
                try {
                       ms.exec("call DesinscritCandidat("+e.getId()+","+this.getId()+")");
                       return true;                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                return false;
            }
            ms.close();
            return false;  
        }
        
        public boolean deInsCand(Personne membre){
            MySQL ms = new MySQL(MYSQL_URL, MYSQL_USER, MYSQL_PSW);
            Personne p = membre;
            
            if (ms.connect()) {
                try {
                       ms.exec("call DesinscritCandidat("+p.getId()+","+this.getId()+")");
                       return true;                   
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                return false;
            }
            ms.close();
            return false;  
        }
        
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		return candidats.remove(candidat);
	}
        
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.remove(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}
}

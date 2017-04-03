package xyz.teamkasand;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import xyz.teamkasand.data.MySQL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import xyz.teamkasand.config.config;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
	private Set<Equipe> equipes;
        private int id;
        private MySQL ms;
        
     
	
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		this(inscriptions,nom,prenom,mail,-1);
                this.ms = new MySQL();
	}
        
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail, int id)
	{
		super(inscriptions, nom, id);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
                this.ms = new MySQL();
                
	}
	/**
	 * Retourne le prénom de la personne.
	 * @return
	 */
	
	public String getPrenom()
	{
		return prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	public void modifPrenom(int id, String name){
            try {
                ms.connect();
                
                ms.execUpdate("call RENAMEPERSONNE("+id+",'"+name+"')");
                        
            } catch (Exception e) {
                e.printStackTrace();
            }
            ms.close();
        }
        
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		return mail;
	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	public void modifMail(int id, String mail){
            try {
                ms.connect();
                
                ms.execUpdate("call CHANGEMAIL("+id+",'"+mail+"')");
                        
            } catch (Exception e) {
                e.printStackTrace();
            }
            ms.close();
        }
                
	public void setMail(String mail)
	{
		this.mail = mail;
	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}
        
	
	boolean add(Equipe equipe)
	{
		return equipes.add(equipe);
	}

	boolean remove(Equipe equipe)
	{
		return equipes.remove(equipe);
	}
	
	@Override
	public void delete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + /*" membre de " + equipes.toString()*/ " " + this.getPrenom();
	}
        
        public ArrayList<String> getNomEquipe(int id)                
	{       
		ArrayList<String> eq = new ArrayList<>();
                try {
                ms.connect();
                
                    ResultSet r = ms.execSelect("call RetourCandidatEquipe("+id+")");
                    while (r.next()) {                        
                        eq.add(r.getNString("NomEquipe"));
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
                ms.close();
                
		return eq;
	}
        
        public ArrayList<Equipe> getEquipeDB(int id)                
	{       
		ArrayList<Equipe> eq = new ArrayList<>();
                 
                try {
                ms.connect();
                
                    ResultSet r = ms.execSelect("call RetourCandidatEquipe("+id+")");
                    while (r.next()) {
                        
                        Equipe e = new Equipe(
                            r.getNString("NomEquipe"),
                            r.getInt("id"));
                        eq.add(e);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
                ms.close();
                
		return eq;
	}
}

package fr.teamKasAnd;

import fr.teamKasAnd.data.MySQL;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
	private Set<Equipe> equipes;
        private int id;
        private static final String MYSQL_URL = "jdbc:mysql://localhost/inscription";
        private static final String MYSQL_USER = "root";
        private static final String MYSQL_PSW = "";
        
     
	
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		this(inscriptions,nom,prenom,mail,-1);
	}
        
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail, int id)
	{
		super(inscriptions, nom, id);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
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
            MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
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
            MySQL ms = new MySQL(this.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
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
}

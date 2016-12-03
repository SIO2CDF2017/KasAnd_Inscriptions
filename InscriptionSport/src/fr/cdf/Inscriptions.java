package fr.cdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collections;
import java.time.LocalDate;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
        private static final String MYSQL_URL = "jdbc:mysql://localhost/inscription";
        private static final String MYSQL_USER = "root";
        private static final String MYSQL_PSW = "";
        
        
	
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();

	public Inscriptions()
	{
	}
	
	/**
	 * Retourne les compétitions.
	 * @return
	 */
	
	public SortedSet<Competition> getCompetitions()
	{       MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
		SortedSet<Competition> competitions = new TreeSet<>();
                try {
                ms.connect();
                
                    ResultSet r = ms.execSelect("call getComp();");
                    while (r.next()) {                        
                        Competition c = inscriptions.createCompetition(r.getNString("Epreuve"), r.getObject("Date_Cloture", LocalDate.class), r.getBoolean("enEquipe"));
                        
                        competitions.add(c);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
                ms.close();
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */
	
	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 */
        
        public SortedSet<Integer> getIdPers(){
            MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            SortedSet<Integer> idpers = new TreeSet<>();
            try {
                ms.connect();
                
                ResultSet rs = ms.execSelect("SELECT idCandidat FROM PERSONNE;");
                while (rs.next()) {                    
                    idpers.add(rs.getInt("idCandidat"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return Collections.unmodifiableSortedSet(idpers);
        }
	
	public SortedSet<Personne> getPersonnes()
	{       MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
		SortedSet<Personne> personnes = new TreeSet<>();
                try {
                ms.connect();
                
                    ResultSet r = ms.execSelect("call getPers();");
                    while (r.next()) {                        
                        Personne p = inscriptions.createPersonne(r.getNString("nom"), r.getNString("prenom"), r.getNString("mail"));
                        
                        personnes.add(p);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
                ms.close();
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les équipes.
	 * @return
	 */
	public SortedSet<Integer> getIdEquipe(){
            MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            SortedSet<Integer> idequip = new TreeSet<Integer>();
            try {
                ms.connect();
                
                ResultSet rs = ms.execSelect("SELECT idCandidat FROM EQUIPE;");
                while (rs.next()) {                    
                    idequip.add(rs.getInt("idCandidat"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return Collections.unmodifiableSortedSet(idequip);
            
        }
        
	public SortedSet<Equipe> getEquipes()
	{
                MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
		SortedSet<Equipe> equipes = new TreeSet<>();
                try {
                ms.connect();
                
                    ResultSet r = ms.execSelect("call getEquipe();");
                    while (r.next()) {                        
                        Equipe e = inscriptions.createEquipe(r.getNString("nom"));
                        
                        equipes.add(e);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
                ms.close();                
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
        public boolean BDCompetition(String nom, LocalDate dateCloture, boolean enEquipe){
            this.createCompetition(nom, dateCloture, enEquipe);
            MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
            if (ms.connect()) {
                    try {
                        ResultSet  rs = ms.execSelect("SELECT * FROM COMPETITION WHERE Epreuve = \""+nom+"\"");
                        if (rs.next()) {
                            return false;
                        }else{
                            ms.exec("call creatComp('"+nom+"','"+dateCloture+"',"+enEquipe+")");
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
	
	public Competition createCompetition(String nom, LocalDate dateCloture, boolean enEquipe)
	{       
		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		competitions.add(competition);
                return competition;
	}

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
        public boolean BDCreatePersonne(String nom, String prenom, String mail){
            this.createPersonne(nom, prenom, mail);
            MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
                            //BDD
                if (ms.connect()) {
                    try {
                        ResultSet  rs = ms.execSelect("SELECT * FROM CANDIDAT, PERSONNE WHERE CANDIDAT.idCandidat = PERSONNE.idCandidat AND CANDIDAT.Nom = \""+nom+"\" AND PERSONNE.Mail = \""+mail+"\" AND PERSONNE.Prenom = \""+prenom+"\"");
                        if (rs.next()) {
                            return false;
                        }else{
                            ms.exec("call creatPers('"+nom+"','"+prenom+"','"+mail+"');");
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
        
	public Personne createPersonne(String nom, String prenom, String mail)
	{       

		Personne personne = new Personne(this, nom, prenom, mail);
		candidats.add(personne);
                return personne;
	}
	
	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	public boolean BDCreateEquipe(String nom){
            this.createEquipe(nom);
            MySQL ms = new MySQL(Inscriptions.MYSQL_URL, this.MYSQL_USER, this.MYSQL_PSW);
                            //BDD
                if (ms.connect()) {
                    try {
                    ResultSet  rs = ms.execSelect("SELECT * FROM CANDIDAT, EQUIPE WHERE CANDIDAT.idCandidat = EQUIPE.idCandidat AND CANDIDAT.Nom = \""+nom+"\"");
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
        
	public Equipe createEquipe(String nom)
	{
                
		Equipe equipe = new Equipe(this, nom);
		candidats.add(equipe);
                return equipe;
	}
	
	void remove(Competition competition)
	{
		competitions.remove(competition);
	}
	
	void remove(Candidat candidat)
	{
		candidats.remove(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		
		if (inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement 
	 * lors d'une exécution ultérieure du programme.
	 * @throws IOException 
	 */
	
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}
	
        /* public static void main(String[] args)
	{
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		Competition flechettes = inscriptions.createCompetition("Mondial de fléchettes", null, false);
		Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb", "azerty"), 
				boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
		flechettes.add(tony);
		Equipe lesManouches = inscriptions.createEquipe("Les Manouches");
		lesManouches.add(boris);
		lesManouches.add(tony);
		System.out.println(inscriptions);
		lesManouches.delete();
		System.out.println(inscriptions);
		try
		{
			inscriptions.sauvegarder();
		} 
		catch (IOException e)
		{
			System.out.println("Sauvegarde impossible." + e);
		}
	} */
}

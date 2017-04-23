package xyz.teamkasand;

import xyz.teamkasand.data.MySQL;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collections;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import xyz.teamkasand.config.config;

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
    
    
    
    private SortedSet<Competition> competitions = new TreeSet<>();
    private SortedSet<Candidat> candidats = new TreeSet<>();
    
    public Inscriptions()
    {
        
    }
    
    /**
     * Retourne les compétitions.
     * @return
     */
    public Set<Integer> getIDComp(){
        Set<Integer> idcomp = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet rs = MySQL.execSelect("SELECT idCompetition FROM competition;");
                while (rs.next()) {
                    idcomp.add(rs.getInt("idCompetition"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Collections.unmodifiableSet(idcomp);
    }
    
    public Set<Competition> getCompetitions()
    {
        Set<Competition> competitions = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet r = MySQL.execSelect("call getComp();");
                while (r.next()) {
                    Competition c = inscriptions.createCompetition(r.getNString("Epreuve"), r.getObject("Date_Cloture", LocalDate.class), r.getBoolean("enEquipe"), r.getInt("idCompetition"));
                    
                    competitions.add(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return Collections.unmodifiableSet(competitions);
    }
    
    public ArrayList<Competition> getCompetitionsInArray()
    {
        
        ArrayList<Competition> competitions = new ArrayList<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet r = MySQL.execSelect("call getComp();");
                while (r.next()) {
                    Competition c = inscriptions.createCompetition(
                            r.getNString("Epreuve"),
                            r.getDate("Date_Cloture").toLocalDate(),
                            r.getBoolean("enEquipe"),
                            r.getInt("idCompetition"));
                    
                    competitions.add(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return competitions;
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
    
    public Set<Integer> getIdPers(){
        Set<Integer> idpers = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet rs = MySQL.execSelect("SELECT idCandidat FROM personne;");
                while (rs.next()) {
                    idpers.add(rs.getInt("idCandidat"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return Collections.unmodifiableSet(idpers);
    }
    
    public Set<Personne> getPersonnes()
    {
        Set<Personne> personnes = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet r = MySQL.execSelect("call getPers();");
                while (r.next()) {
                    Personne p = inscriptions.createPersonne(r.getNString("nom"), r.getNString("prenom"), r.getNString("mail"), r.getInt("ID"));
                    
                    personnes.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return Collections.unmodifiableSet(personnes);
    }
    
    public ArrayList<Personne> getPersonnesInArray()
    {
        ArrayList<Personne> personnes = new ArrayList<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet r = MySQL.execSelect("call getPers();");
                while (r.next()) {
                    Personne p = inscriptions.createPersonne(
                            r.getNString("nom"),
                            r.getNString("prenom"),
                            r.getNString("mail"),
                            r.getInt("ID"));
                    if(p != null){
                        personnes.add(p);
                    }
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return personnes;
    }
    /**
     * Retourne toutes les équipes.
     * @return
     */
    public Set<Integer> getIdEquipe(){
        Set<Integer> idequip = new LinkedHashSet<Integer>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet rs = MySQL.execSelect("SELECT idCandidat FROM equipe;");
                while (rs.next()) {
                    idequip.add(rs.getInt("idCandidat"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return Collections.unmodifiableSet(idequip);
        
    }
    
    public Set<Equipe> getEquipes()
    {
        Set<Equipe> equipes = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet r = MySQL.execSelect("call getEquipe();");
                while (r.next()) {
                    Equipe e = inscriptions.createEquipe(r.getNString("nom"), r.getInt("id"));
                    
                    equipes.add(e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return Collections.unmodifiableSet(equipes);
    }
    
    
    public ArrayList<Equipe> getEquipesInArray()
    {
        ArrayList<Equipe> equipes = new ArrayList<>();
        if (MySQL.isConnect()) {
            try {
                
                ResultSet r = MySQL.execSelect("call getEquipe();");
                while (r.next()) {
                    Equipe e = inscriptions.createEquipe(r.getNString("nom"), r.getInt("id"));
                    
                    equipes.add(e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return equipes;
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
        if (MySQL.isConnect()) {
            try {
                ResultSet  rs = MySQL.execSelect("SELECT * FROM competition WHERE Epreuve = \""+nom+"\"");
                if (rs.next()) {
                    return false;
                }else{
                    MySQL.exec("call creatComp('"+nom+"','"+dateCloture+"',"+enEquipe+")");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return false;
        }
        return false;
        
    }
    
    public Competition createCompetition(String nom, LocalDate dateCloture, boolean enEquipe){
        return this.createCompetition(nom, dateCloture, enEquipe, -1);
    }
    public Competition createCompetition(String nom, LocalDate dateCloture, boolean enEquipe, int id)
    {
        Competition competition = new Competition(this, nom, dateCloture, enEquipe, id);
        competitions.add(competition);
        return competition;
    }
    
    /**
     * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
     * de constructeur public dans {@link Personne}.
     *
     * @param nom
     * @param prenom
     * @param mail
     * @return
     */
    
    public boolean BDCreatePersonne(String nom, String prenom, String mail){
        this.createPersonne(nom, prenom, mail);
        //BDD
        if (MySQL.isConnect()) {
            try {
                ResultSet  rs = MySQL.execSelect("SELECT * FROM candidat, personne WHERE candidat.idCandidat = personne.idCandidat AND candidat.Nom = \""+nom+"\" AND personne.Mail = \""+mail+"\" AND personne.Prenom = \""+prenom+"\"");
                if (rs.next()) {
                    return false;
                }else{
                    MySQL.exec("call creatPers('"+nom+"','"+prenom+"','"+mail+"');");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return false;
        }
        
        return false;
    }
    
    
    public Personne createPersonne(String nom, String prenom, String mail){
        return this.createPersonne(nom, prenom, mail, -1);
    }
    
    public Personne createPersonne(String nom, String prenom, String mail, int id)
    {
        
        Personne personne = new Personne(this, nom, prenom, mail, id);
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
        //BDD
        if (MySQL.isConnect()) {
            try {
                ResultSet  rs = MySQL.execSelect("SELECT * FROM `candidat` WHERE `idCandidat` NOT IN (SELECT `IdCandidat` FROM `personne`) AND candidat.Nom = '"+nom+"'");
                if (rs.next()) {
                    return false;
                }else{
                    MySQL.exec("call creatEquipe('"+nom+"')");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return false;
        }
        return false;
    }
    
    public Equipe createEquipe(String nom){
        return createEquipe(nom, -1);
    }
    
    public Equipe createEquipe(String nom, int id)
    {
        Equipe equipe = new Equipe(this, nom, id);
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
    
    public Set<String> getinsCand(int idcand){
        Set<String> comp = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                ResultSet rs = MySQL.execSelect("call COMPETCANDIDAT("+idcand+")");
                while (rs.next()) {
                    comp.add(rs.getNString("Epreuve"));
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return Collections.unmodifiableSet(comp);
        
    }
    
    public Set<String> getinscomp(int idcomp){
        Set<String> pers = new LinkedHashSet<>();
        if (MySQL.isConnect()) {
            try {
                ResultSet rs = MySQL.execSelect("call candidatsInscrits("+idcomp+")");
                while (rs.next()) {
                    pers.add(rs.getNString("nom"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return pers;
    }
    
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
    
    public boolean suppers(int id){
        if (MySQL.isConnect()) {
            try {
                
                MySQL.exec("call DELETEPERS("+id+")");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean supequip(int id){
        if (MySQL.isConnect()) {
            try {
                
                MySQL.exec("call DELETEEQUIP("+id+")");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    /**
     * Supprime la compétition de l'application.
     */
    public boolean supComp(int id){
        if (MySQL.isConnect()) {
            try {
                
                MySQL.exec("call suprComp("+id+")");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean estInscrit(int idcand, int idComp){
        if (MySQL.isConnect()) {
            try {
                MySQL.exec("call EstInscrit();");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean inscrirCand(int idCand, int idComp){
        if (MySQL.isConnect()) {
            try {
                MySQL.exec("call ajouterEquipeACompetition"+"("+idCand+","+idComp+");");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean inscrirCand(Candidat cand, int idComp){
        if (MySQL.isConnect()) {
            try {
                MySQL.connect();
                MySQL.exec("call ajouterEquipeACompetition"+"("+cand.getId()+","+idComp+");");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
    public Personne createPersonne(String nString, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

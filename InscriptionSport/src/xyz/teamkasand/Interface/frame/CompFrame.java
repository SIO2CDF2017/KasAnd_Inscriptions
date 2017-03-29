 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import xyz.teamkasand.Competition;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Interface.frame.model.NonEditableModel;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class CompFrame extends JFrame {
    private JTable table; 
   private JScrollPane p;
   private Competition[] competitions;
    
    public CompFrame(Inscriptions i, Frame f){
        
        String[] header = {"Nom","Date de cloture","En equipe ?"};
        
        JFrame th = this;
        
        ArrayList<Competition> comp = i.getCompetitionsInArray();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        this.competitions = comp.toArray(new Competition[0]);
        
        Object[][] datas = new Object[comp.size()][];
        for (int j = 0 ; j<comp.size(); j++) {
            Competition c = comp.get(j);
            
            
            datas[j] = new Object[3];
            datas[j][0] = c.getNom();
            datas[j][1] = df.format(Date.from(c.getDateCloture().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            datas[j][2] = c.estEnEquipe()?"Oui":"Non";
        }
        
            JButton btn_retour = new JButton("Retour");
            JButton btn_create = new JButton("Créé une competition");
            
            btn_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                th.dispose();
            }
        });
            
            btn_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ee = false;
                JTextField nom = new JTextField();
                JSpinner anne = new JSpinner();
                JSpinner moi = new JSpinner();
                JSpinner jour = new JSpinner();
                Object[] ob1 = {
                    "nom",nom
                };
             
            
                
                
                Object[] ob2 = {
                    "Année date de cloture",anne,
                    "Mois date de cloture",moi,
                    "Jour date de cloture",jour
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob1, "Ajouter une competition",JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(!nom.getText().isEmpty()){
                        int k = JOptionPane.showConfirmDialog(th, "En equipe ? ", "En equipe ?", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (k == 0) 
                            ee = true;
                        else if (k == 1)
                            ee = false;
                        int m = JOptionPane.showConfirmDialog(th, ob2, "Ajouter une competition",JOptionPane.OK_CANCEL_OPTION);
                        if (m == JOptionPane.OK_OPTION)  {
                            if((int)anne.getValue()>0 && (int)moi.getValue()>0 && (int)jour.getValue()>0){
                                try {
                                    LocalDate auj = LocalDate.now();
                                    LocalDate date = LocalDate.of((int)anne.getValue(), (int)moi.getValue(), (int)jour.getValue());
                                    if(date.isAfter(auj)){
                                        if (i.BDCompetition(nom.getText(), date, ee)) {
                                            th.dispose();
                                            f.getm_comp().doClick();
                                        }
                                    }
                                    else
                                        JOptionPane.showMessageDialog(th, "Erreur : La date de cloture doit être supperieur à la date d'aujourd'hui.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                                catch(Exception ex){
                                        JOptionPane.showMessageDialog(th, "Erreur : La date rentre est invalide.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }    
                            }
                            else
                                JOptionPane.showMessageDialog(th, "Erreur : la valeur 0 pour le jour, le mois, ou l'année est impossible.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(th, "Erreur : aucun nom entre.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        
            
        
        JButton btn_modif = new JButton("Modifier une compétition");
            btn_modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                if(uuid == -1)
                    return;
                
                        String name="" ;
                        LocalDate dateActuelle = LocalDate.MAX;
                        ArrayList<Competition> cComp = i.getCompetitionsInArray();
                        boolean IdExist = false;
                        for(Competition comp : cComp){
                            if(uuid==comp.getId()){
                                name = comp.getNom();
                                dateActuelle = comp.dateClotureInscriptions(comp.getId());
                                IdExist = true;
                            }
                        }
                        if(IdExist){
                            JTextField nom = new JTextField(name);
                            JSpinner jour = new JSpinner();
                            JSpinner mois = new JSpinner();
                            JSpinner annee = new JSpinner();
                            jour.setValue(dateActuelle.getDayOfMonth());
                            mois.setValue(dateActuelle.getMonthValue());
                            annee.setValue(dateActuelle.getYear());
                            Object[] ob2 = {
                                "Nom",nom,
                                "jour",jour,
                                "mois",mois,
                                "annee",annee,
                            };
                            int k = JOptionPane.showConfirmDialog(th, ob2,"Modifier le nom d'une Compétition",JOptionPane.OK_CANCEL_OPTION);
                            if(k == JOptionPane.OK_OPTION) {
                                if(!nom.getText().isEmpty()){
                                    try{
                                        LocalDate dateFin = LocalDate.of((int)annee.getValue(),(int)mois.getValue(),(int)jour.getValue());
                                        Competition compet = i.createCompetition(nom.getText(), dateFin, true);
                                        try{
                                            
                                            if(dateFin.isBefore(dateActuelle))
                                                JOptionPane.showMessageDialog(th, "Erreure : La date saisie doit être suppérieur à la date initial", "OK", JOptionPane.INFORMATION_MESSAGE);
                                            else{
                                                compet.modifNom(uuid, nom.getText());
                                                compet.modifDateCloture(uuid, dateFin);
                                                JOptionPane.showMessageDialog(th, "La compétition à bien été modifié", "OK", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            th.dispose();
                                            f.getm_comp().doClick();
                                        }
                                        catch(Exception echecModif){             
                                            JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    catch(Exception exCompDate){
                                        JOptionPane.showMessageDialog(th, "Erreur : La date saisie est invalide", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(th, "Erreur : aucun champs ne doit être vide. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(th, "Erreur : Id Inconnu. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
            }
        });
            
            
        JButton btn_sup = new JButton("Supprimé une Competition");
        btn_sup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                
                    if(i.supComp(uuid)){
                            JOptionPane.showMessageDialog(th, "La Competition à bien été supprimé", "OK", JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_comp().doClick();                      
                    }else{
                        JOptionPane.showMessageDialog(th, "Unne erreur c'est produit", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });    
            
         
        JButton btn_IncCand = new JButton("Inscrire une Personne/Equipe");
        btn_IncCand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                Competition comp = getSelectedCompetion();
                if(uuid == -1)
                    return;             
                    
                    if(comp.estEnEquipe()){
                        Equipe[] eq = i.getEquipesInArray().toArray(new Equipe[0]);
                
                        Equipe ee = (Equipe)JOptionPane.showInputDialog(th, "Choisir une equipe", "Choisir", 
                        JOptionPane.QUESTION_MESSAGE, null, eq, null);
                        if (ee == null)
                            return;
                        if(i.inscrirCand(ee, uuid)){
                            JOptionPane.showMessageDialog(th, "L'équipe à été inscrite", "ok",JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_comp().doClick();
                        }
                        else
                           JOptionPane.showMessageDialog(th, "Une erreur s'est produite", "ERROR",JOptionPane.ERROR_MESSAGE); 
                    }
                    else{
                        Personne[] p = i.getPersonnesInArray().toArray(new Personne[0]);
                        Personne pp = (Personne)JOptionPane.showInputDialog(th,"Choisir une personne","Choisir",JOptionPane.QUESTION_MESSAGE, null, p, null);
                        if(pp==null)
                            return;
                        if(i.inscrirCand(pp,uuid)){
                            JOptionPane.showMessageDialog(th, "La personne à été inscrite", "ok",JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_comp().doClick();
                        }else
                           JOptionPane.showMessageDialog(th, "Une erreur s'est produite", "ERROR",JOptionPane.ERROR_MESSAGE); 
                    }
            }
        });
        
        JButton btn_DesincCand = new JButton("Desinscrire une Personne/Equipe");
        btn_DesincCand.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int uuid = getSelectedId();
                Competition comp = getSelectedCompetion();
                if(uuid == -1)
                    return;
                if(comp.estEnEquipe()){
                    
                }
                
            }
        }); 
        
         table = new JTable(new NonEditableModel(datas, header));
         //table.setEnabled(false);
         
         JPanel btn2 = new JPanel();
         btn2.setLayout(new BorderLayout());
         btn2.add(btn_IncCand, BorderLayout.WEST);
         
         JPanel btn = new JPanel();
         btn.setLayout(new BorderLayout());
         btn.add(btn_create, BorderLayout.WEST);
         btn.add(btn_modif, BorderLayout.CENTER);
         btn.add(btn_sup, BorderLayout.EAST);
         btn.add(btn2, BorderLayout.SOUTH);
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    } 
    
    private Competition getSelectedCompetion() {
        if(table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(table, "Vous devez selectionner une pazjepoazjepoajz", "", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        return this.competitions[table.getSelectedRow()];
    }
     
    private int getSelectedId() {
        Competition o = getSelectedCompetion();
        return (o == null) ? -1 : o.getId();
    }
}
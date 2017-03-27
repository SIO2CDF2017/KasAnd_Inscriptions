/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;
import xyz.teamkasand.config.config;
import xyz.teamkasand.mail.Mail;

/**
 *
 * @author asandolo
 */
public class PersFrame extends JFrame {  
     private JScrollPane p;        
     public PersFrame(Inscriptions i, Frame f){
         
         
        String[] header = {"#","Nom","Prenom","Mail","Equipe"};
        
        ArrayList<Personne> pers = i.getPersonnesInArray();
         
        Object[][] datas = new Object[pers.size()][];
        for (int j = 0 ; j<pers.size(); j++) {
            Personne p = pers.get(j);
            
            datas[j] = new Object[5];
            datas[j][0] = p.getId();
            datas[j][1] = p.getNom();
            datas[j][2] = p.getPrenom();
            datas[j][3] = p.getMail();
            String listPers = "";
            ArrayList<String> checkAppartenir = p.getNomEquipe(p.getId());
            if(!checkAppartenir.isEmpty()){
                for(String ca : checkAppartenir){
                    listPers = listPers + " " + ca + ", ";
                }
            }
            else
                listPers = "N'a aucune equipe";
            datas[j][4] = listPers;
           
            
        }
        
        
         JTable table = new JTable(datas, header);
         table.setEnabled(false);
         
         JFrame th = this;
         
         JButton btn_create = new JButton("Créer une personne");
         JButton btn_modif = new JButton("Modifier une personne");
         JButton btn_retour = new JButton("Retour");
         btn_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               th.dispose();
            }
        });
         
         
         btn_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nom = new JTextField();
                JTextField prenom = new JTextField();
                JTextField mail = new JTextField();
                
                Object[] ob = {
                  "Nom",nom,
                  "Prenom",prenom,
                  "Mail",mail,
                };
                int j = JOptionPane.showConfirmDialog(th, ob,"Créer une personne",JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(!nom.getText().isEmpty() || !prenom.getText().isEmpty() || !mail.getText().isEmpty() ){
                        if(i.BDCreatePersonne(nom.getText(), prenom.getText(), mail.getText())){
                            JOptionPane.showMessageDialog(th, "La personne à bien été créée", "OK", JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_pers().doClick();
                        }else{
                            JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(th, "Erreur : Tous les champs doivent être remplie", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        
        
        btn_modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSpinner id = new JSpinner();
                Object[] ob = {
                    "Id De la personne à modifier", id,
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob,"Modifier une personne",JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if((int)id.getValue()>=0){
                        String name="",prename="",email = "";
                        ArrayList<Personne> cPers = i.getPersonnesInArray();
                        boolean IdExist = false;
                        for(Personne p : cPers){
                            if((int)id.getValue()==p.getId()){
                                name = p.getNom();
                                prename = p.getPrenom();
                                email = p.getMail();
                                IdExist = true;
                            }
                        }
                        if(IdExist){
                            
                            JTextField nom = new JTextField(name);
                            JTextField prenom = new JTextField(prename);
                            JTextField mail = new JTextField(email);
                            Object[] ob2 = {
                                "Nom",nom,
                                "Prenom",prenom,
                                "Mail",mail,
                            };
                            int k = JOptionPane.showConfirmDialog(th, ob2,"Modifier une personne",JOptionPane.OK_CANCEL_OPTION);
                            if(k == JOptionPane.OK_OPTION) {
                                if(!nom.getText().isEmpty() && !prenom.getText().isEmpty() &&  !mail.getText().isEmpty()){
                                    Personne p = i.createPersonne(name, prename, email);
                                    try{
                                        p.modifNom((int)id.getValue(), nom.getText());
                                        p.modifPrenom((int)id.getValue(), prenom.getText());
                                        p.modifMail((int)id.getValue(), mail.getText());
                                        JOptionPane.showMessageDialog(th, "La personne à bien été modifié", "OK", JOptionPane.INFORMATION_MESSAGE);
                                        th.dispose();
                                        f.getm_pers().doClick();
                                    }
                                    catch(Exception echecModif){             
                                        JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(th, "Erreur : aucun champs ne doit être vide. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(th, "Erreur : Id Inconnu. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(th, "Erreur : Id Inconnu. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        }); 
         
         
        JButton btn_sup = new JButton("Supprimé une Personne");
        btn_sup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSpinner id = new JSpinner();
                
                Object[] ob = {
                    "Id de la personne à suprimé",id
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob, "Supprimé une personne", JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(i.suppers((int)id.getValue())){
                            JOptionPane.showMessageDialog(th, "La personne à bien été supprimé", "OK", JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_pers().doClick();                      
                    }else{
                        JOptionPane.showMessageDialog(th, "Unne erreur c'est produit", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        JButton btn_mail = new JButton("Envoyer un mail a une personne");
        btn_mail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSpinner id = new JSpinner();
                JTextArea text = new JTextArea();
                boolean checkId = false;
                
                Object[] ob = {
                    "ID de la personne",id,
                    "Texte du mail",text
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob,"Envoyer un mail", JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    String mail = "";
                    ArrayList<Personne> pers = i.getPersonnesInArray();
                    for (Personne per : pers) {
                        if (per.getId() == (int)id.getValue()) {
                            checkId = true;
                            mail = per.getMail();
                        }
                    }
                    if (checkId) {
                        if (!text.getText().isEmpty()) {
                            
                            Mail m = new Mail();
                            if(m.sendMail(mail, "CONTACT INSCRIPTION", text.getText())){
                                JOptionPane.showMessageDialog(th, "Le mail a été encoyé", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(th, "La personne n'existe pas", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }else {
                            JOptionPane.showMessageDialog(th, "Le contenu est vide", "ERROR", JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(th, "La personne n'existe pas", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
         
        
        
         JPanel btn2 = new JPanel();
         btn2.setLayout(new BorderLayout());
         btn2.add(btn_mail, BorderLayout.WEST);
         
         
         JPanel btn = new JPanel();
         btn.setLayout(new BorderLayout());
         btn.add(btn_create, BorderLayout.EAST);
         btn.add(btn_modif, BorderLayout.CENTER);
         btn.add(btn_sup, BorderLayout.WEST);
         btn.add(btn2, BorderLayout.SOUTH);
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

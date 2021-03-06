/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Interface.frame.model.NonEditableModel;
import xyz.teamkasand.Personne;
import xyz.teamkasand.Utils.mail.Mail;

/**
 *
 * @author asandolo
 */
public class PersFrame extends JFrame {  
     private JScrollPane p; 
     private JTable table;
     private Personne[] personnes;
     
     public PersFrame(Inscriptions i, Frame f){
         
         
        String[] header = {"Nom","Prenom","Mail","Equipe"};
        
        ArrayList<Personne> pers = i.getPersonnesInArray();
        this.personnes = pers.toArray(new Personne[0]);
        
        Object[][] datas = new Object[pers.size()][];
        for (int j = 0 ; j<pers.size(); j++) {
            Personne p = pers.get(j);
            
            datas[j] = new Object[4];
            datas[j][0] = p.getNom();
            datas[j][1] = p.getPrenom();
            datas[j][2] = p.getMail();
            String listPers = "";
            ArrayList<String> checkAppartenir = p.getNomEquipe(p.getId());
            if(!checkAppartenir.isEmpty()){
                for(String ca : checkAppartenir){
                    listPers = listPers + " " + ca + ", ";
                }
            }
            else
                listPers = "N'a aucune equipe";
            datas[j][3] = listPers;
           
            
        }
        
        
         table = new JTable(
                new NonEditableModel(datas, header));
         //table.setEnabled(false);
         
                 
         JFrame th = this;
         
         JButton btn_create = new JButton("Créer une personne");
         JButton btn_modif = new JButton("Modifier la personne");
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
                int uuid = getSelectedId();
                if( uuid == -1 )
                    return;
                
                
                
                    String name="",prename="",email = "";
                    ArrayList<Personne> cPers = i.getPersonnesInArray();
                    boolean IdExist = false;
                    for(Personne p : cPers){
                        if(uuid==p.getId()){
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
                        int k = JOptionPane.showConfirmDialog(th, ob2,"Modifier la personne",JOptionPane.OK_CANCEL_OPTION);
                        if(k == JOptionPane.OK_OPTION) {
                            if(!nom.getText().isEmpty() && !prenom.getText().isEmpty() &&  !mail.getText().isEmpty()){
                                Personne p = i.createPersonne(name, prename, email);
                                try{
                                    p.modifNom(uuid, nom.getText());
                                    p.modifPrenom(uuid, prenom.getText());
                                    p.modifMail(uuid, mail.getText());
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
            }
        }); 
         
         
        JButton btn_sup = new JButton("Supprimer la personne");
        btn_sup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                if (uuid == -1)
                    return;
                    if(i.suppers(uuid)){
                            JOptionPane.showMessageDialog(th, "La personne à bien été supprimé", "OK", JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_pers().doClick();                      
                    }else{
                        JOptionPane.showMessageDialog(th, "Unne erreur c'est produit", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
            }      
        });
        
        JButton btn_mail = new JButton("Envoyer un mail à la personne");
        btn_mail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JTextArea text = new JTextArea();
                text.setPreferredSize(new Dimension(300, 150));
                int uuid = getSelectedId();
                boolean checkId = false;
                if (uuid == -1)
                    return;
                
                Object[] ob = {
                    "Texte du mail",text
                };
                
                
               /* text.addKeyListener(new KeyListener() {
                    @Override public void keyTyped(KeyEvent e) {}
                    @Override public void keyPressed(KeyEvent e) {}

                    @Override public void keyReleased(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                            
                        }
                    }
                });*/
                
                
               
                int j = JOptionPane.showConfirmDialog(th, ob,
                        "Envoyer un mail", JOptionPane.OK_CANCEL_OPTION);
                    
                if (j == JOptionPane.OK_OPTION) {
                    String mail = "";
                    ArrayList<Personne> pers = i.getPersonnesInArray();
                    for (Personne per : pers) {
                        if (per.getId() == uuid) {
                            checkId = true;
                            mail = per.getMail();
                        }
                    }
                    if (checkId) {
                        if (!text.getText().isEmpty()) {
                            
                            Mail m = new Mail();
                            if(m.sendMail(mail, "CONTACT INSCRIPTION", text.getText())){
                                JOptionPane.showMessageDialog(th, "Le mail a été envoyé", "ERROR", JOptionPane.INFORMATION_MESSAGE);
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
         
        
        JButton btn_addEquip = new JButton("Ajouter a une equipe");
        btn_addEquip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                if (uuid == -1)
                    return;
                
                Equipe[] eq = i.getEquipesInArray().toArray(new Equipe[0]);
                
                Equipe ee = (Equipe)JOptionPane.showInputDialog(th, "Choisir une equipe", "Choisir", 
                        JOptionPane.QUESTION_MESSAGE, null, eq, null);
                
                if (ee == null)
                    return;
                
               if(ee.addBD(uuid)){
                   JOptionPane.showMessageDialog(th, "La personne a bien été ajoutée", "ok",JOptionPane.INFORMATION_MESSAGE);
                   th.dispose();
                   f.getm_pers().doClick(); 
               }else{
                   JOptionPane.showMessageDialog(th, "Une erreur s'est produite", "ERROR",JOptionPane.ERROR_MESSAGE);
               }
            }
        });
        
        JButton btn_supEquip = new JButton("Supprimer d'une équipe");
        btn_supEquip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int uuid = getSelectedId();
            Personne pers = getSelectedPersonne();
            
           Equipe[] eq = pers.getEquipeDB(uuid).toArray(new Equipe[0]);
           
           
            Equipe ee  = (Equipe)JOptionPane.showInputDialog(th, "Choisir une equipe", "Choisir", 
                        JOptionPane.QUESTION_MESSAGE, null, eq, null);
            
                if (ee == null)
                    return;
               
               if(ee.supPers(pers)){
                   JOptionPane.showMessageDialog(th, "La personne a bien été supprimée", "ok",JOptionPane.INFORMATION_MESSAGE);
                   th.dispose();
                   f.getm_pers().doClick(); 
               }else{
                   JOptionPane.showMessageDialog(th, "Une erreur s'est produite", "ERROR",JOptionPane.ERROR_MESSAGE);
               }

            }
        });
                
         JPanel btn2 = new JPanel();
         btn2.setLayout(new BorderLayout());
         btn2.add(btn_mail, BorderLayout.WEST);
         btn2.add(btn_supEquip, BorderLayout.CENTER);
         btn2.add(btn_addEquip, BorderLayout.EAST);
         
         
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
        this.requestFocusInWindow();
    }


    private Personne getSelectedPersonne() {
        if(table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(table, "Vous devez selectionner une pazjepoazjepoajz", "", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        return this.personnes[ table.getSelectedRow() ];
    }
     
    private int getSelectedId() {
        Personne o = getSelectedPersonne();
        return (o == null) ? -1 : o.getId();
    }
}

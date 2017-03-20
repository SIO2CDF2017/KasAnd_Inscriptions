/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class PersFrame extends JFrame {
     private JScrollPane p;        
     public PersFrame(Inscriptions i){
         
         
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
                  "Mail",mail
                };
                int j = JOptionPane.showConfirmDialog(th, ob,"Créer une personne",JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(!nom.getText().isEmpty() || !prenom.getText().isEmpty() || !mail.getText().isEmpty() ){
                        if(i.BDCreatePersonne(nom.getText(), prenom.getText(), mail.getText())){
                            JOptionPane.showMessageDialog(th, "La personne à bien été créée", "OK", JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            th.setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(th, "Erreur : Tous les champs doivent être remplie", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
         
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn_create, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

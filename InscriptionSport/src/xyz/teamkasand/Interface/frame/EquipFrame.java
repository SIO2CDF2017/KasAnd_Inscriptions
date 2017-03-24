/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class EquipFrame extends JFrame {
    
    private JScrollPane p;
    
    public EquipFrame(Inscriptions i, Frame f){
        
        String[] header = {"#","Nom","membres"};
        JFrame th = this;
        
        ArrayList<Equipe> eq = i.getEquipesInArray();
         
        Object[][] datas = new Object[eq.size()][];
        for (int j = 0 ; j<eq.size(); j++) {
            Equipe e = eq.get(j);
            
            datas[j] = new Object[3];
            datas[j][0] = e.getId();
            datas[j][1] = e.getNom();
            String listMembreEq = " ";
            ArrayList<Personne> checkPers = e.getMembresEquipe(e.getId());
            if(!checkPers.isEmpty()){
                for(Personne p : checkPers){
                   listMembreEq = listMembreEq + p.getNom() + " " + p.getPrenom()+ ", ";
                }
            } else{
                listMembreEq = "Equipe sans membre ";
            }
            datas[j][2] = listMembreEq;
        }
        
        JButton btn_retour = new JButton("Retour");
        JButton btn_add = new JButton("Ajouter une Equipe");
        btn_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                th.dispose();
            }
        });
        
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nom = new JTextField();
                
                Object[] ob = {
                    "Nom",nom
                };
                int j = JOptionPane.showConfirmDialog(th, ob, "Créée une équipe", JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(!nom.getText().isEmpty()){
                        if (i.BDCreateEquipe(nom.getText())) {
                            JOptionPane.showMessageDialog(th, "L'équipe à bien été créé","OK",JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_equip().doClick();
                        }else{
                            JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(th,"Erreur : Tous les champs doivent être remplie", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
            
            JButton btn_modif = new JButton("Modifier le nom d'une équipe");
            btn_modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSpinner id = new JSpinner();
                Object[] ob = {
                    "Id De l'équipe à modifier", id,
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob,"Modifier le nom d'une équipe",JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if((int)id.getValue()>=0){
                        String name="" ;
                        ArrayList<Equipe> cPers = i.getEquipesInArray();
                        boolean IdExist = false;
                        for(Equipe eq : cPers){
                            if((int)id.getValue()==eq.getId()){
                                name = eq.getNom();

                                IdExist = true;
                            }
                        }
                        if(IdExist){
                            
                            JTextField nom = new JTextField(name);
                            Object[] ob2 = {
                                "Nom",nom,
                            };
                            int k = JOptionPane.showConfirmDialog(th, ob2,"Modifier le nom d'une équipe",JOptionPane.OK_CANCEL_OPTION);
                            if(k == JOptionPane.OK_OPTION) {
                                if(!nom.getText().isEmpty()){
                                    Equipe eq = i.createEquipe(name);
                                    try{
                                        eq.modifNom((int)id.getValue(), nom.getText());
                                        JOptionPane.showMessageDialog(th, "L'équipe à bien été modifié", "OK", JOptionPane.INFORMATION_MESSAGE);
                                        th.dispose();
                                        f.getm_equip().doClick();
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
        
        
         JTable table = new JTable(datas, header);
         table.setEnabled(false);
         
        JButton btn_sup = new JButton("Supprimé une equipe");
        btn_sup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSpinner id = new JSpinner();
                
                Object[] ob = {
                    "Id de l'Equipe à suprimé",id
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob, "Supprimé une Equipe", JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(i.supequip((int)id.getValue())){
                            JOptionPane.showMessageDialog(th, "L'Equipe à bien été supprimé", "OK", JOptionPane.INFORMATION_MESSAGE);
                            th.dispose();
                            f.getm_equip().doClick();                      
                    }else{
                        JOptionPane.showMessageDialog(th, "Unne erreur c'est produit", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
         
         JPanel btn = new JPanel();
         btn.setLayout(new BorderLayout());
         btn.add(btn_add, BorderLayout.EAST);
         btn.add(btn_modif,BorderLayout.CENTER);
         btn.add(btn_sup, BorderLayout.WEST);
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

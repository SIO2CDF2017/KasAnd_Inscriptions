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
import xyz.teamkasand.Interface.frame.model.NonEditableModel;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class EquipFrame extends JFrame {
    
    private JTable table;
    private JScrollPane p;
    private Equipe[] equipes;
    
    public EquipFrame(Inscriptions i, Frame f){
        
        String[] header = {"Nom","membres"};
        JFrame th = this;
        
        ArrayList<Equipe> eq = i.getEquipesInArray();
        this.equipes = eq.toArray(new Equipe[0]);
        
        Object[][] datas = new Object[eq.size()][];
        for (int j = 0 ; j<eq.size(); j++) {
            Equipe e = eq.get(j);
            
            datas[j] = new Object[2];
            datas[j][0] = e.getNom();
            String listMembreEq = " ";
            ArrayList<Personne> checkPers = e.getMembresEquipe(e.getId());
            if(!checkPers.isEmpty()){
                for(Personne p : checkPers){
                   listMembreEq = listMembreEq + p.getNom() + " " + p.getPrenom()+ ", ";
                }
            } else{
                listMembreEq = "Equipe sans membre ";
            }
            datas[j][1] = listMembreEq;
        }
        
        JButton btn_retour = new JButton("Retour");
        JButton btn_add = new JButton("Créer une Equipe");
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
                int j = JOptionPane.showConfirmDialog(th, ob, "Créer une équipe", JOptionPane.OK_CANCEL_OPTION);
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
        
            
            JButton btn_modif = new JButton("Modifier le nom");
            btn_modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                Equipe eq = getSelectedEquipe();
                if(uuid == -1)
                    return;
                JTextField name = new JTextField(eq.getNom());
                Object[] ob = {"Nom",name};
                int j = JOptionPane.showConfirmDialog(th,name,"Modifier le nom de l'équipe",JOptionPane.OK_CANCEL_OPTION);
                if(j == JOptionPane.OK_OPTION){
                    if(eq.modifNom(uuid,name.getText())){
                        JOptionPane.showMessageDialog(th, "Le nom de l'équipe à bien été modifié","OK",JOptionPane.INFORMATION_MESSAGE);
                        th.dispose();
                        f.getm_equip().doClick();
                    }
                    else
                       JOptionPane.showMessageDialog(th,"Erreur : une erreur est survenue", "ERROR", JOptionPane.ERROR_MESSAGE); 
                }
            }
        });
        JButton btn_supP = new JButton("Supprimer une personne");
        btn_supP.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int uuid = getSelectedId();
                if(uuid == -1)
                    return;
                Equipe eq = getSelectedEquipe();
                Personne [] p = eq.getMembresEquipe(uuid).toArray(new Personne[0]);
                Personne pe = (Personne)JOptionPane.showInputDialog(th,"Choisir la personne","Choisir", JOptionPane.QUESTION_MESSAGE,null,p,null);
                if(pe==null)
                    return;
                if(eq.supPers(pe)){
                    JOptionPane.showMessageDialog(th, "La personne a bien été supprimé de l'équipe", "ok",JOptionPane.INFORMATION_MESSAGE);
                    th.dispose();
                    f.getm_equip().doClick();
                }
                else
                    JOptionPane.showMessageDialog(th, "Une erreur s'est produite", "ERROR",JOptionPane.ERROR_MESSAGE); 
            }
        });
        JButton btn_addP = new JButton("Ajouter une personne");
        btn_addP.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Equipe eq = getSelectedEquipe();
                int uuid = getSelectedId();
                if(uuid == -1)
                    return;
                Personne[] pers = i.getPersonnesInArray().toArray(new Personne[0]);
                Personne pe = (Personne)JOptionPane.showInputDialog(th,"Choisir une Personne","Choisir",JOptionPane.QUESTION_MESSAGE,null,pers,null);
                if(pe==null)
                    return;
                if(eq.addBD(pe)){
                    JOptionPane.showMessageDialog(th, "La personne a bien été ajouté à l'équipe", "ok",JOptionPane.INFORMATION_MESSAGE);
                    th.dispose();
                    f.getm_equip().doClick();
                }
                else
                    JOptionPane.showMessageDialog(th, "Une erreur s'est produite, verifiez que la personne n'appartient pas déjà à cette équipe", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
         
        JButton btn_sup = new JButton("Supprimer une equipe");
        btn_sup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int uuid = getSelectedId();
                
                if(uuid == -1)
                    return;
                if(i.supequip(uuid)){
                    JOptionPane.showMessageDialog(th, "L'équipe à bien été supprimée","OK",JOptionPane.INFORMATION_MESSAGE);
                    th.dispose();
                    f.getm_equip().doClick();
                }
                else
                    JOptionPane.showMessageDialog(th,"Erreur : une erreur est survenue", "ERROR", JOptionPane.ERROR_MESSAGE); 
            }
        });
         
        
        table = new JTable(new NonEditableModel(datas, header));
         
         JPanel btn2 = new JPanel();
         btn2.setLayout(new BorderLayout());
         btn2.add(btn_addP, BorderLayout.LINE_START);
         btn2.add(btn_supP, BorderLayout.LINE_END);
         
         JPanel btn = new JPanel();
         btn.setLayout(new BorderLayout());
         btn.add(btn_add, BorderLayout.LINE_START);
         btn.add(btn_modif,BorderLayout.CENTER);
         btn.add(btn_sup, BorderLayout.LINE_END);
         btn.add(btn2, BorderLayout.SOUTH);
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    private Equipe getSelectedEquipe() {
        if(table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(table, "Vous devez selectionner une pazjepoazjepoajz", "", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        return this.equipes[ table.getSelectedRow() ];
    }
     
    private int getSelectedId() {
        Equipe o = getSelectedEquipe();
        return (o == null) ? -1 : o.getId();
    }
}

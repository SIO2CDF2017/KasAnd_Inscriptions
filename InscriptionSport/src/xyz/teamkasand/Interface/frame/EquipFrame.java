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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;

/**
 *
 * @author asandolo
 */
public class EquipFrame extends JFrame {
    
    private JScrollPane p;
    
    public EquipFrame(Inscriptions i){
        
        String[] header = {"#","Nom"};
        JFrame th = this;
        
        ArrayList<Equipe> eq = i.getEquipesInArray();
         
        Object[][] datas = new Object[eq.size()][];
        for (int j = 0 ; j<eq.size(); j++) {
            Equipe e = eq.get(j);
            
            datas[j] = new Object[2];
            datas[j][0] = e.getId();
            datas[j][1] = e.getNom();
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
                    if (i.BDCreateEquipe(nom.getText())) {
                        JOptionPane.showMessageDialog(th, "L'équipe à bien été créé","OK",JOptionPane.INFORMATION_MESSAGE);
                        th.dispose();
                        th.setVisible(true);
                    }else{
                      JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        
         JTable table = new JTable(datas, header);
         table.setEnabled(false);
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn_add, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    } 
}

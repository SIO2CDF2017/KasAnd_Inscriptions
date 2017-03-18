/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import com.sun.org.apache.xpath.internal.operations.Mod;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class PersFrame extends JFrame {
     private JScrollPane p;
     private JTable table;
     
     public PersFrame(Inscriptions i ) {
         
         JFrame th = this;
         this.updateTable(i);
         
         JButton btn_create = new JButton("Créer une personne");
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
                    if(i.BDCreatePersonne(nom.getText(), prenom.getText(), mail.getText())){
                        JOptionPane.showMessageDialog(th, "La personne à bien été créée", "OK", JOptionPane.INFORMATION_MESSAGE);
                        updateTable(i);
                    }else{
                        JOptionPane.showMessageDialog(th, "Une erreur est survenue ! Merci de contacter votre administrateur", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
         
         
        this.setLayout(new BorderLayout());
        this.add(btn_create, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
     
     private void updateTable(Inscriptions i) {
        String[] header = {"#","Nom","Prenom","Mail"};
        
        ArrayList<Personne> pers = i.getPersonnesInArray();
         
        Object[][] datas = new Object[pers.size()][];
        for (int j = 0 ; j<pers.size(); j++) {
            Personne p = pers.get(j);
            
            datas[j] = new Object[4];
            datas[j][0] = p.getId();
            datas[j][1] = p.getNom();
            datas[j][2] = p.getPrenom();
            datas[j][3] = p.getMail();
            
            System.out.println(p.getNom());
        }
        
        table = new JTable(datas, header);
         table.setEnabled(false);
         ((AbstractTableModel)table.getModel()).fireTableDataChanged();
         this.repaint();
     }
}

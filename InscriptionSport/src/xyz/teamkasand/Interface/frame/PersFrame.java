/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class PersFrame extends JFrame {
     private JScrollPane p;
     private Object[] datas;
        
     public PersFrame(Inscriptions i ){
         
         
        String[] header = {"#","Nom","Prenom","Mail"};
        
         ArrayList<Personne> pers = i.getPersonnesInArray();
         
         int it = 0;
         int j = 0;
         while (!pers.isEmpty()) {             
           //  datas[i];
         }
        
         
         JTable table = new JTable();
         
         this.p = new JScrollPane(table);
         this.p.setLayout(new ScrollPaneLayout());
         
         this.setContentPane(p);
    }
}

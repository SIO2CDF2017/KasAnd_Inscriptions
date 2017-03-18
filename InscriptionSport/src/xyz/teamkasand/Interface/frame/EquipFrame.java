/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
        
        ArrayList<Equipe> eq = i.getEquipesInArray();
         
        Object[][] datas = new Object[eq.size()][];
        for (int j = 0 ; j<eq.size(); j++) {
            Equipe e = eq.get(j);
            
            datas[j] = new Object[2];
            datas[j][0] = e.getId();
            datas[j][1] = e.getNom();
        }
        
        
         JTable table = new JTable(datas, header);
         table.setEnabled(false);
         
         this.p = new JScrollPane(table);
         this.p.setLayout(new ScrollPaneLayout());
         
         this.setContentPane(p);
    } 
}

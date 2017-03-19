/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import xyz.teamkasand.Competition;
import xyz.teamkasand.Equipe;
import xyz.teamkasand.Inscriptions;

/**
 *
 * @author asandolo
 */
public class CompFrame extends JFrame {
    
    private JScrollPane p;
    
    public CompFrame(Inscriptions i){
        
        String[] header = {"#","Nom","Date de cloture","En equipe ?"};
        
        ArrayList<Competition> comp = i.getCompetitionsInArray();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        
        Object[][] datas = new Object[comp.size()][];
        for (int j = 0 ; j<comp.size(); j++) {
            Competition c = comp.get(j);
            
            
            
            datas[j] = new Object[4];
            datas[j][0] = c.getId();
            datas[j][1] = c.getNom();
            datas[j][2] = df.format(Date.from(c.getDateCloture().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            datas[j][3] = c.estEnEquipe()?"Oui":"Non";
        }
        
        
         JTable table = new JTable(datas, header);
         table.setEnabled(false);
         
         this.p = new JScrollPane(table);
         this.p.setLayout(new ScrollPaneLayout());
         
         this.setContentPane(p);
    } 
}

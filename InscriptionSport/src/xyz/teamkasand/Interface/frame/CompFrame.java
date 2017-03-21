/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import xyz.teamkasand.Competition;
import xyz.teamkasand.Inscriptions;

/**
 *
 * @author asandolo
 */
public class CompFrame extends JFrame {
    
    private JScrollPane p;
    
    public CompFrame(Inscriptions i, Frame f){
        
        String[] header = {"#","Nom","Date de cloture","En equipe ?"};
        
        JFrame th = this;
        
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
        
            JButton btn_retour = new JButton("Retour");
            JButton btn_create = new JButton("Créé une competition");
            
            btn_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                th.dispose();
            }
        });
            
            btn_create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ee = false;
                JTextField nom = new JTextField();
                JSpinner anne = new JSpinner();
                JSpinner moi = new JSpinner();
                JSpinner jour = new JSpinner();
                Object[] ob1 = {
                    "nom",nom
                };
             
            
                
                
                Object[] ob2 = {
                    "Année date de cloture",anne,
                    "Mois date de cloture",moi,
                    "Jour date de cloture",jour
                };
                
                int j = JOptionPane.showConfirmDialog(th, ob1, "Ajouter une competition",JOptionPane.OK_CANCEL_OPTION);
                if (j == JOptionPane.OK_OPTION) {
                    if(!nom.getText().isEmpty()){
                        int k = JOptionPane.showConfirmDialog(th, "En equipe ? ", "En equipe ?", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (k == 0) 
                            ee = true;
                        else if (k == 1)
                            ee = false;
                        int m = JOptionPane.showConfirmDialog(th, ob2, "Ajouter une competition",JOptionPane.OK_CANCEL_OPTION);
                        if (m == JOptionPane.OK_OPTION)  {
                            if((int)anne.getValue()>0 && (int)moi.getValue()>0 && (int)jour.getValue()>0){
                                try {
                                    LocalDate auj = LocalDate.now();
                                    LocalDate date = LocalDate.of((int)anne.getValue(), (int)moi.getValue(), (int)jour.getValue());
                                    if(date.isAfter(auj)){
                                        if (i.BDCompetition(nom.getText(), date, ee)) {
                                            th.dispose();
                                            f.getm_comp().doClick();
                                        }
                                    }
                                    else
                                        JOptionPane.showMessageDialog(th, "Erreur : La date de cloture doit être supperieur à la date d'aujourd'hui.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                                catch(Exception ex){
                                        JOptionPane.showMessageDialog(th, "Erreur : La date rentre est invalide.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    
                                }
                                
                            }
                            else
                                JOptionPane.showMessageDialog(th, "Erreur : la valeur 0 pour le jour, le mois, ou l'année est impossible.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(th, "Erreur : aucun nom entre.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        
         JTable table = new JTable(datas, header);
         table.setEnabled(false);
         
        this.setLayout(new BorderLayout());
        this.add(btn_retour, BorderLayout.NORTH);
        this.add(btn_create, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    } 
}

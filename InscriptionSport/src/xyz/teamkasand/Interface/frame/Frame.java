/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import xyz.teamkasand.Inscriptions;
import xyz.teamkasand.data.MySQL;

/**
 *
 * @author asandolo
 */
public class Frame extends JFrame{
    
    private Inscriptions i;
    //private MySQL ms;
    private final JPanel p,footer;
    private final Content c;
    private final JLabel footext;
    private final JMenuItem mi_home, mi_pers, mi_equip, mi_comp, mi_quit;
    private final JMenuBar mb;
    private String page = null;
    private Dimension d = new Dimension(700, 700); 
    private Frame f;
    public Frame(Inscriptions i){
        this.f = this;
        this.i = i;
        //this.ms = ms;
        BtnListener b = new BtnListener();
        
        
        this.footext = new JLabel("par : Alexandre SANDOLO et Victor KASPERSKI");
        
        this.footer = new JPanel();
        this.footer.add(this.footext);
        
        this.c = new Content();
        
        this.mi_quit = new JMenuItem("Quitter");
        this.mi_quit.setActionCommand("q");
        this.mi_quit.addActionListener(b);
        
        this.mi_home = new JMenuItem("Home");
        this.mi_home.setActionCommand("a");
        this.mi_home.addActionListener(b);

        this.mi_pers = new JMenuItem("Personne");
        this.mi_pers.setActionCommand("p");
        this.mi_pers.addActionListener(b);

        this.mi_equip = new JMenuItem("Equipe");
        this.mi_equip.setActionCommand("e");
        this.mi_equip.addActionListener(b);

        this.mi_comp = new JMenuItem("Competition");
        this.mi_comp.setActionCommand("c");
        this.mi_comp.addActionListener(b);        
        
        this.mb = new JMenuBar();
        this.mb.add(mi_home);
        this.mb.add(mi_pers);
        this.mb.add(mi_equip);
        this.mb.add(mi_comp);
        this.mb.add(mi_quit);
      
        
        this.p = new JPanel();
        this.p.setLayout(new BorderLayout());
        this.p.add(this.c, BorderLayout.CENTER);
        this.p.add(this.footer, BorderLayout.SOUTH);
        

       
        this.setTitle("Inscriptions");
        this.setSize(this.d);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(this.p);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {}
            
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(c,"Etes-vous certain de vouloir quitter?",
                       "Quitter Inscriptions ?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    MySQL.close();
                    System.exit(0);
            }
            
        });
         this.setJMenuBar(mb);

        
    }
    public String getPage(){
        return this.page;
    }

    public void setPage(String p){
        this.page = p;
    } 
    
    private class Content extends JPanel{
        
        @Override
        public void paintComponent(Graphics g){
           if(page == null) setPage("home");
            switch(getPage()){
                case "home": 
                     HomeFrame h = new HomeFrame();
                     h.paintComponent(g);
                    break;
            }   
        }
    }

    private class BtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {        
            try {
                switch(e.getActionCommand()){
                    case "a":
                        setPage("home");
                        p.repaint();
                        break;
                    case "p":
                        PersFrame pf = new PersFrame(i, f);
                        pf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        pf.setTitle("Personnes");
                        pf.setSize(d);
                        pf.setLocationRelativeTo(null);
                        pf.setResizable(false);
                        pf.setVisible(true);
                        break;
                    case "e":
                        EquipFrame eq = new EquipFrame(i, f);
                        eq.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        eq.setTitle("Equipes");
                        eq.setSize(d);
                        eq.setLocationRelativeTo(null);
                        eq.setResizable(false);
                        eq.setVisible(true);
                        break;
                    case "c":
                        CompFrame co = new CompFrame(i, f);
                        co.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        co.setTitle("Competitions");
                        co.setSize(d);
                        co.setLocationRelativeTo(null);
                        co.setResizable(false);
                        co.setVisible(true);
                        break;
                    case "q":
                        if(JOptionPane.showConfirmDialog(c,"Etes-vous certain de vouloir quitter?",
                                "Quitter Inscriptions ?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            MySQL.close();
                            System.exit(0);
                       break;
                }
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        
    }
    
    
    public JMenuItem getm_home(){
        return this.mi_home;
    }

    public JMenuItem getm_comp(){
        return this.mi_comp;
    }
        
    public JMenuItem getm_equip(){
        return this.mi_equip;
    }
    
    public JMenuItem getm_pers(){
        return this.mi_pers;
    }
}

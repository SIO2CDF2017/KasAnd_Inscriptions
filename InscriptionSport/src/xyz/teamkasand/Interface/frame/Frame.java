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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import xyz.teamkasand.Inscriptions;

/**
 *
 * @author asandolo
 */
public class Frame extends JFrame{
    
    private Inscriptions i;
    private final JPanel p,footer;
    private final Content c;
    private final JLabel footext;
    private final JMenuItem mi_home, mi_pers, mi_equip, mi_comp, mi_quit;
    private final JMenuBar mb;
    private String page = null;
    
    public Frame(Inscriptions i){
        this.i = i;
        BtnListener b = new BtnListener();
        Dimension d = new Dimension(600, 600);
        
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
        this.p.add(this.mb, BorderLayout.NORTH);
        this.p.add(this.c, BorderLayout.CENTER);
        this.p.add(this.footer, BorderLayout.SOUTH);
        

        
        
        this.setTitle("Inscriptions");
        this.setSize(d);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(this.p);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                       "Quitter Inscriptions ?", JOptionPane.YES_NO_OPTION) == 0)
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
                    g.drawString("Home", 10, 20);
                    break;
                case "pers":
                    g.drawString("Pers", 10, 20);
                    break;
                case "equipe":
                    g.drawString("Equipe", 10, 20);
                    break;
                case "comp":
                    g.drawString("Comp", 10, 20);
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
                        setPage("pers");
                        p.repaint();
                        break;
                    case "e":
                        setPage("equipe");
                        p.repaint();
                        break;
                    case "c":
                        setPage("comp");
                        p.repaint();
                        break;
                    case "q":
                        if(JOptionPane.showConfirmDialog(c,"Etes-vous certain de vouloir quitter?",
                                "Quitter Inscriptions ?", JOptionPane.YES_NO_OPTION) == 0)
                        System.exit(0);
                       break;
                }
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        
    }
    
    
}

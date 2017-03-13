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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import xyz.teamkasand.Inscriptions;

/**
 *
 * @author asandolo
 */
public class Frame extends JFrame{
    
    private Inscriptions i;
    private final JPanel p,footer, mb;
    private final Content c;
    private JLabel footext;
    private final JButton btn_home, btn_pers, btn_equip, btn_comp, btn_quit;
    private String page = null;
    
    public Frame(){
        //this.i = i;
        BtnListener b = new BtnListener();
        Dimension d = new Dimension(600, 600);
        
        this.footext = new JLabel("par : Alexandre SANDOLO et Victor KASPERSKI");
        
        this.footer = new JPanel();
        this.footer.add(this.footext);
        
        this.c = new Content();
        
        this.btn_quit = new JButton("Quitter");
        this.btn_quit.setActionCommand("q");
        this.btn_quit.addActionListener(b);
        
        this.btn_home = new JButton("Home");
        this.btn_home.setActionCommand("a");
        this.btn_home.addActionListener(b);

        this.btn_pers = new JButton("Personne");
        this.btn_pers.setActionCommand("p");
        this.btn_pers.addActionListener(b);

        this.btn_equip = new JButton("Equipe");
        this.btn_equip.setActionCommand("e");
        this.btn_equip.addActionListener(b);

        this.btn_comp = new JButton("Competition");
        this.btn_comp.setActionCommand("c");
        this.btn_comp.addActionListener(b);        
        
        this.mb = new JPanel();
        this.mb.add(btn_home);
        this.mb.add(btn_pers);
        this.mb.add(btn_equip);
        this.mb.add(btn_comp);
        this.mb.add(btn_quit);
      
        
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
                        break;
                    case "p":
                        setPage("pers");
                        mb.repaint();
                        c.repaint();
                        break;
                    case "e":
                        setPage("equipe");
                        mb.repaint();
                        c.repaint();
                        break;
                    case "c":
                        setPage("comp");
                        mb.repaint();
                        c.repaint();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.data;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author asandolo
 */
public abstract class MySQL {
    
    private static final String URL = "jdbc:mysql://217.182.50.221/inscription";
    private static final String USER = "ins";
    private static final  String PASS = "yolo";
    private static Connection dbConnect = null;
    private static Statement dbStatement = null;
    private static boolean connected = false;
    
    /*@Deprecated
    public MySQL(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
         this.connect();
    }
    
    public MySQL(){
        this.connect();
    }*/
    
    
    public static boolean connect() {
        if (isConnect())
            return false;
        
        LoaderFrame lf = new LoaderFrame();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnect = DriverManager.getConnection(URL, USER, PASS);
            dbStatement = dbConnect.createStatement();
            connected = true;
            lf.closeLoader();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        lf.closeLoader();
        return false;
    }
    
    
    public static boolean isConnect(){
        return connected;
    }
    
    public static ResultSet execSelect(String s){
        try {
            ResultSet rs = dbStatement.executeQuery(s);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static int execUpdate(String s){
        try {
            int rs = dbStatement.executeUpdate(s);
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public static boolean exec(String s){
        try {
            boolean rs = dbStatement.execute(s);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }  
    
    public static void close(){
        try {
            connected = false;
            dbStatement.close();
            dbConnect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private static class LoaderFrame {
        private JFrame frame;
        
        public LoaderFrame() {
            JLabel lbl = new JLabel("Chargement en cours, veuillez patienter");
            lbl.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
            
            this.frame = new JFrame("Loader");
            this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.frame.setLocationRelativeTo(null);
            this.frame.setContentPane(lbl);
            this.frame.setAlwaysOnTop(true);
            this.frame.setVisible(true);
            this.frame.pack();
            
            for (Frame f : JFrame.getFrames())
                f.setEnabled(false);
        }
        
        public void closeLoader() {
            try{Thread.sleep(100);}catch(InterruptedException ex){}
            
            for (Frame f : JFrame.getFrames())
                f.setEnabled(true);
            
            this.frame.dispose();
        }
    }
}

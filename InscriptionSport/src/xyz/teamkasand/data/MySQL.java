/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import xyz.teamkasand.config.config;

/**
 *
 * @author asandolo
 */
public class MySQL {
    
    private String url = "jdbc:mysql://217.182.50.221/inscription";
    private String user = "ins";
    private String pass = "yolo";
    private Connection dbConnect = null;
    private Statement dbStatement = null;
    private config c = new config();
    private final HashMap<String, Object> conf = c.getConfigMysql();
    private boolean connected = false;
    
    @Deprecated
    public MySQL(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    public MySQL(){
        this.connect();
    }
    
    
    public boolean connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.dbConnect = DriverManager.getConnection(this.url, this.user, this.pass);
            this.dbStatement = this.dbConnect.createStatement();
            connected = true;
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    
    public boolean isConnect(){
        return connected;
    }
    
    public ResultSet execSelect(String s){
        try {
            ResultSet rs = this.dbStatement.executeQuery(s);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int execUpdate(String s){
        try {
            int rs = this.dbStatement.executeUpdate(s);
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public boolean exec(String s){
        try {
            boolean rs = this.dbStatement.execute(s);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }  
    
    public void close(){
        try {
            connected = false;
            this.dbStatement.close();
            this.dbConnect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

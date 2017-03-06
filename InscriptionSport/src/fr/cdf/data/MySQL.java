/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cdf.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author asandolo
 */
public class MySQL {
    
    private String url = "";
    private String user = "";
    private String pass = "";
    private Connection dbConnect = null;
    private Statement dbStatement = null;
    
    
    public MySQL(String url, String user, String pass){
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    
    public boolean connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.dbConnect = DriverManager.getConnection(this.url, this.user, this.pass);
            this.dbStatement = this.dbConnect.createStatement();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
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
            this.dbStatement.close();
            this.dbConnect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

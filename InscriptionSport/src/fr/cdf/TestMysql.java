package fr.cdf;

import java.sql.ResultSet;
import java.sql.SQLException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asandolo
 */
public class TestMysql {
    public static void main(String[] args){
        MySQL ms = new MySQL("jdbc:mysql://localhost:3306/ins", "root", "");
        if (ms.connect()) {
            try {
                ResultSet rs = ms.execSelect("SELECT * FROM test;");
                if (rs != null) {
                    while (rs.next()) {                        
                        System.out.println("ID : "+rs.getString("id")+" libele : "+rs.getString("libele"));
                    }
                }
                
                int rs2 = ms.execUpdate("INSERT INTO test VALUES(4,'JEan')");
                if (rs2 == -1) {
                    System.out.println("Erreur Insert");
                }
            } catch (Exception e) {
                
            }
        }else{
            System.out.println("Erreur Connexion !");
        }
        ms.close();
    }
}

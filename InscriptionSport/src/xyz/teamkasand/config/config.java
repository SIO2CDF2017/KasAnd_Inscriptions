package xyz.teamkasand.config;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asandolo
 */
public class config {
    private static final String MYSQL_URL = "jdbc:mysql://217.182.50.221/inscription";
    private static final String MYSQL_USER = "ins";
    private static final String MYSQL_PSW = "yolo";
    
    private static final String MAIL_HOST = "smtp.asandolo.fr";
    private static final int MAIL_PORT = 25;
    private static final String MAIL_USER = "ins@asandolo.fr";
    private static final String MAIL_PASS = "hehWzM!W73";
    private static final String MAIL_SENDER = "ins@asandolo.fr";
    private final HashMap mysql = new HashMap<>();
    private final HashMap mail = new HashMap<>();
    
    public config(){

        
        mail.put("host", MAIL_HOST);
        mail.put("port", MAIL_PORT);
        mail.put("user", MAIL_USER);
        mail.put("pass", MAIL_PASS);
        mail.put("sender", MAIL_SENDER);
        
        mysql.put("url", MYSQL_URL);
        mysql.put("user", MYSQL_USER);
        mysql.put("pass", MYSQL_PSW);
        
    }
    
    /**
     *
     * @return retourne les configuration Mail
     */
    public HashMap getConfigMail(){
        return this.mail;
    }
    
    /**
     *.
     * @return retourne les configuration Mysql
     */
    @Deprecated
    public HashMap getConfigMysql(){
        return this.mysql;
    }    
}

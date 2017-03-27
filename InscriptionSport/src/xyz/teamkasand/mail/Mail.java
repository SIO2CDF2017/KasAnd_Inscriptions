/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.mail;

import java.util.HashMap;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import xyz.teamkasand.config.config;

/**
 *
 * @author asandolo
 */
public class Mail {
    private config c = new config();
    private HashMap conf = c.getConfigMail();
    private String MAIL_HOST;
    private int MAIL_PORT;
    private String MAIL_USER;
    private String MAIL_PASS;
    private String MAIL_SENDER;
    
    public Mail(){
        this.MAIL_HOST = (String)conf.get("host");
        this.MAIL_PORT = (int)conf.get("port");
        this.MAIL_USER = (String)conf.get("user");
        this.MAIL_PASS = (String)conf.get("pass");
        this.MAIL_SENDER = (String)conf.get("sender");
    }
    
    public boolean sendMail(String to, String sujet, String msg){
        try {
            Email m = new SimpleEmail();
            m.setHostName(MAIL_HOST);
            m.setSmtpPort(MAIL_PORT);
            m.setAuthentication(MAIL_USER, MAIL_PASS);
            m.setFrom(MAIL_SENDER);
            
            m.addTo(to);
            m.setSubject(sujet);
            m.setMsg(msg);
            m.send();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

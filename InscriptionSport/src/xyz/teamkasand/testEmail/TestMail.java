/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.testEmail;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author asandolo
 */
public class TestMail {
    private static final String MAIL_HOST = "smtp.asandolo.fr";
    private static final int MAIL_PORT = 25;
    private static final String MAIL_USER = "ins@asandolo.fr";
    private static final String MAIL_PASS = "hehWzM!W73";
    



public static void main(String[] args){
   Email email = new SimpleEmail();
   email.setHostName(MAIL_HOST);
   email.setSmtpPort(MAIL_PORT);
   email.setAuthentication(MAIL_USER, MAIL_PASS);
   
        try {
            email.setFrom("ins@asandolo.fr");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail from JAVAAAAAAAAAAAAAAAA ! :-)");
            email.addTo("contact@asandolo.fr");
            email.send();
            System.out.println("OK !");
        } catch (EmailException ex) {
            Logger.getLogger(TestMail.class.getName()).log(Level.SEVERE, null, ex);
        }

}

//email.setHostName("smtp.googlemail.com");
//email.setSmtpPort(465);
//email.setAuthenticator(new DefaultAuthenticator("username", "password"));
}

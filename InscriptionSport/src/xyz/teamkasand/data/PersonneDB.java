/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.data;

import java.util.HashMap;
import xyz.teamkasand.Personne;

/**
 *
 * @author asandolo
 */
public class PersonneDB {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private HashMap<Integer, Personne> pers = new HashMap<Integer, Personne>();
    
    public PersonneDB(int id, String nom, String prenom, String mail){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }
    
    
}

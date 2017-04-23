/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author asandolo
 */
public class HomeFrame extends JPanel
{     
     JPanel th = this;
     @Override
     public void paintComponent(Graphics g){
         g.drawString("BIENVENUE !", 319, 10);
         g.drawString("TUTORIEL : ", 325, 50);


         g.drawString("I/ Personne", 100, 70);
         g.drawString(" - Créer : Dans Personne -> Créer une personne ", 10, 90);
         g.drawString(" - Supprimer : Dans Personne -> Selectionez une personne -> Supprimer la personne ", 10, 110);
         g.drawString(" - Modifier : Dans Personne -> Selectionez une personne -> Modifier la personne ", 10, 130);
         g.drawString(" - Ajouter a une équipe : Dans Personne -> Selectionez une personne -> Ajouter à  une équipe", 10, 150);
         g.drawString(" - Supprimer d'une équipe : Dans Personne -> Selectionez une équipe -> Supprimer d'une équipe", 10, 170);
         g.drawString(" - Envoyer un mail : Dans Personne -> Selectionez une équipe -> Envoyer un mail a la peronnne", 10, 190);
         
         g.drawString("II/ Equipe", 100, 210);
         g.drawString(" - Créer : Dans Equipe -> Créer une équipe ", 10, 230);
         g.drawString(" - Supprimer : Dans Equipe -> Selectionez une équipe -> Supprimer l'équipe ", 10, 250);
         g.drawString(" - Modifier : Dans Equipe -> Selectionez une équipe -> Modifier l'équipe ", 10, 270);
         g.drawString(" - Ajouter une Equipe : Dans Equipe -> Selectionez une équipe -> Ajouter une personne", 10, 290);
         g.drawString(" - Supprimer une Equipe : Dans Equipe -> Selectionez une équipe -> Supprimer une personne", 10, 310);
         
         g.drawString("III/ Compétition", 100, 330);
         g.drawString(" - Créer : Dans Compétition -> Créer une compétition ", 10, 350);
         g.drawString(" - Supprimer : Dans Compétition -> Selectionez une compétition -> Supprimer la compétion ", 10, 370);
         g.drawString(" - Modifier : Dans Compétition -> Selectionez une compétition -> Modifier la compétition ", 10, 390);
         g.drawString(" - Inscrire un candidat : Dans Compétition -> Selectionez une compétition -> Incrire un candidat", 10, 410);
         g.drawString(" - Deinscrire un candidat : Dans Compétition -> Selectionez une compétition -> Deincrire un candidat", 10, 430);
         
     }
}

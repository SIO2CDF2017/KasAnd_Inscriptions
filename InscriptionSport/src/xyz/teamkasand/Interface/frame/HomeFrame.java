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
     @Override
     public void paintComponent(Graphics g){
         g.drawString("Home", 10, 20);
     }
}
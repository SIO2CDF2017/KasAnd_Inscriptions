/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.teamkasand.Interface.frame.model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class NonEditableModel extends DefaultTableModel {
    public NonEditableModel(Object[][] datas, String[] cols){
        super(datas, cols);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    
}

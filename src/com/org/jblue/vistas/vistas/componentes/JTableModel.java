/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.vistas.vistas.componentes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class JTableModel extends DefaultTableModel {

    private final boolean[] canEdit;
    private Class[] types;

    public JTableModel(String... columnas) {
        super(columnas, 0);
        this.canEdit = new boolean[]{
            false, false, false, false, false, false, false, false
        };
    }

    public void setTypes(Class... types) {
        this.types = types;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public void setCellEditable(int columnIndex, boolean value) {
        this.canEdit[columnIndex] = value;
    }
}

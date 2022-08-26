/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox;

import com.org.jblue.Sistema.cache.cacheGUI.estructura.FuncionesModelo;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloComboBox;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloComboBoxCN;
import com.org.jblue.modelo.objetos.OTitulares;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author jp
 */
public class ModeloComboBoxTitulares implements FuncionesModelo, ModeloComboBoxCN {

    private final ModeloComboBox<OTitulares> modelo;
    
    public ModeloComboBoxTitulares(ArrayList<OTitulares> lista) {
        this.modelo = new ModeloComboBox(lista);
    }

    @Override
    public void llenar() {
        for (OTitulares o : modelo.getLista()) {
            modelo.getComboBox().addItem(o.getNombre() + " " + o.getAp() + " " + o.getAm());
        }
    }
    
    @Override
    public void vaciar() {
        
    }
    
    @Override
    public void recargar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void setComboBox(JComboBox<String> o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public JComboBox<String> getComboBox() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

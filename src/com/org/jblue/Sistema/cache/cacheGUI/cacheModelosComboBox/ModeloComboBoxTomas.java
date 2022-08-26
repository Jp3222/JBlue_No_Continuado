/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox;

import com.org.jblue.Sistema.cache.cacheGUI.estructura.FuncionesModelo;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloComboBox;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloComboBoxCN;
import com.org.jblue.modelo.objetos.OTomas;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author jp
 */
public class ModeloComboBoxTomas implements FuncionesModelo, ModeloComboBoxCN {

    private final ModeloComboBox<OTomas> modelo;

    public ModeloComboBoxTomas(ArrayList<OTomas> lista) {
        this.modelo = new ModeloComboBox(lista);
    }

    @Override
    public void llenar() {
        modelo.llenar();
    }

    @Override
    public void vaciar() {
        modelo.vaciar();
    }

    @Override
    public void recargar() {
        modelo.recargar();
    }

    @Override
    public void setComboBox(JComboBox<String> o) {
        modelo.setComboBox(o);
    }

    @Override
    public JComboBox<String> getComboBox() {
        return modelo.getComboBox();
    }

}

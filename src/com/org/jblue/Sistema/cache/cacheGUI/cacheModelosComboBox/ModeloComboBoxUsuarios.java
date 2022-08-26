/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox;

import com.org.jblue.Sistema.cache.cacheGUI.estructura.FuncionesModelo;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloComboBox;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloComboBoxCN;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author jp
 */
public class ModeloComboBoxUsuarios implements FuncionesModelo, ModeloComboBoxCN {

    private final ModeloComboBox<Objeto> modelo;
    private final ArrayList<Objeto> lista;

    public ModeloComboBoxUsuarios(ArrayList<OTitulares> l1, ArrayList<OConsumidores> l2) {
        lista = new ArrayList<>();
        for (OTitulares oTitulares : l1) {
            lista.add(oTitulares);
        }
        for (OConsumidores oConsumidores : l2) {
            lista.add(oConsumidores);
        }

        modelo = new ModeloComboBox(lista);
    }

    @Override
    public void llenar() {
        for (Objeto objeto : lista) {
            String[] info = objeto.getInfo();
            modelo.getComboBox().addItem(info[1] + " " + info[2] + " " + info[3]);
        }
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

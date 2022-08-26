/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox;

import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.OTomas;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public abstract class CacheModeloComboBox {

    private ModeloComboBoxCalles modeloComboBoxCalles;
    private ModeloComboBoxTitulares modeloComboBoxTitulares;
    private ModeloComboBoxTomas modeloComboBoxTomas;
    private ModeloComboBoxUsuarios modeloComboBoxUsuarios;

    public ModeloComboBoxCalles getModeloComboBoxCalles(ArrayList<OCalles> lista) {
        if (modeloComboBoxCalles == null) {
            modeloComboBoxCalles = new ModeloComboBoxCalles(lista);
        }
        return modeloComboBoxCalles;
    }

    public ModeloComboBoxCalles getModeloComboBoxCalles() {
        return modeloComboBoxCalles;
    }

    public ModeloComboBoxTitulares getModeloComboBoxTitulares(ArrayList<OTitulares> lista) {
        if (modeloComboBoxTitulares == null) {
            modeloComboBoxTitulares = new ModeloComboBoxTitulares(lista);
        }
        return modeloComboBoxTitulares;
    }

    public ModeloComboBoxTitulares getModeloComboBoxTitulares() {
        return modeloComboBoxTitulares;
    }

    public ModeloComboBoxTomas getModeloComboBoxTomas(ArrayList<OTomas> lista) {
        if (modeloComboBoxTomas == null) {
            modeloComboBoxTomas = new ModeloComboBoxTomas(lista);
        }
        return modeloComboBoxTomas;
    }

    public ModeloComboBoxTomas getModeloComboBoxTomas() {
        return modeloComboBoxTomas;
    }

    public ModeloComboBoxUsuarios getModeloComboBoxUsuarios(ArrayList<OTitulares> l1, ArrayList<OConsumidores> l2) {
        if (modeloComboBoxUsuarios == new ModeloComboBoxUsuarios(l1, l2)) {

        }
        return modeloComboBoxUsuarios;
    }

    public ModeloComboBoxUsuarios getModeloComboBoxUsuarios() {
        return modeloComboBoxUsuarios;
    }

}

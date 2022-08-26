/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas;

import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosComboBox.CacheModeloComboBox;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.OTomas;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public abstract class CacheModeloTablas extends CacheModeloComboBox {

    private ModeloTablaCalles modeloTablaCalles;
    private ModeloTablaConsumidores modeloTablaConsumidores;
    private ModeloTablaTitulares modeloTablaTitulares;
    private ModeloTablaTomas modeloTablaTomas;

    public ModeloTablaCalles getModeloTablaCalles(ArrayList<OCalles> lista, DefaultTableModel modelo) {
        if (modeloTablaCalles == null) {
            modeloTablaCalles = new ModeloTablaCalles(lista, modelo);
        }
        return modeloTablaCalles;
    }

    public ModeloTablaCalles getModeloTablaCalles() {
        return modeloTablaCalles;
    }

    public ModeloTablaConsumidores getModeloTablaConsumidores(ArrayList<OConsumidores> lista, DefaultTableModel modelo) {
        if (modeloTablaConsumidores == null) {
            modeloTablaConsumidores = new ModeloTablaConsumidores(lista, modelo);
        }
        return modeloTablaConsumidores;
    }

    public ModeloTablaConsumidores getModeloTablaConsumidores() {
        return modeloTablaConsumidores;
    }

    public ModeloTablaTitulares getModeloTablaTitulares(ArrayList<OTitulares> lista, DefaultTableModel modelo) {
        if (modeloTablaTitulares == null) {
            modeloTablaTitulares = new ModeloTablaTitulares(lista, modelo);
        }
        return modeloTablaTitulares;
    }

    public ModeloTablaTitulares getModeloTablaTitulares() {
        return modeloTablaTitulares;
    }

    public ModeloTablaTomas getModeloTablaTomas(ArrayList<OTomas> lista, DefaultTableModel modelo) {
        if (modeloTablaTomas == null) {
            modeloTablaTomas = new ModeloTablaTomas(lista, modelo);
        }
        return modeloTablaTomas;
    }

    public ModeloTablaTomas getModeloTablaTomas() {
        return modeloTablaTomas;
    }

}

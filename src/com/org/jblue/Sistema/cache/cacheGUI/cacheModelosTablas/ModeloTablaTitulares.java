/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas;

import com.org.jblue.Sistema.cache.cacheGUI.estructura.FuncionesModelo;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloTablas;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloTablasCN;
import com.org.jblue.modelo.objetos.OTitulares;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class ModeloTablaTitulares implements FuncionesModelo, ModeloTablasCN {

    private final ModeloTablas<OTitulares> envotorio;

    public ModeloTablaTitulares(ArrayList<OTitulares> lista, DefaultTableModel modelo) {
        this.envotorio = new ModeloTablas(lista, modelo);
    }

    @Override
    public void llenar() {
        envotorio.llenar();
    }

    @Override
    public void vaciar() {
        envotorio.vaciar();
    }

    @Override
    public void recargar() {
        envotorio.recargar();
    }

    @Override
    public DefaultTableModel getModelo() {
        return envotorio.getModelo();
    }

}

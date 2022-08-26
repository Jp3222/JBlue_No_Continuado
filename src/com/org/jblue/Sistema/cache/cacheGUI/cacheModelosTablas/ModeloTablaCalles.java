/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas;

import com.org.jblue.Sistema.cache.cacheGUI.estructura.FuncionesModelo;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloTablas;
import com.org.jblue.Sistema.cache.cacheGUI.estructura.ModeloTablasCN;
import com.org.jblue.modelo.objetos.OCalles;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class ModeloTablaCalles implements FuncionesModelo, ModeloTablasCN {

    private final ModeloTablas<OCalles> envoltorio;

    public ModeloTablaCalles(ArrayList<OCalles> lista, DefaultTableModel modelo) {
        this.envoltorio = new ModeloTablas(lista, modelo);
    }

    @Override
    public void llenar() {
        envoltorio.llenar();
    }

    @Override
    public void vaciar() {
        envoltorio.vaciar();
    }

    @Override
    public void recargar() {
        envoltorio.recargar();
    }

    @Override
    public DefaultTableModel getModelo() {
        return envoltorio.getModelo();
    }

}

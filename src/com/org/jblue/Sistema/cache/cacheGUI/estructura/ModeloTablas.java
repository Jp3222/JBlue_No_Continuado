/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.estructura;

import com.org.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 * @param <T>
 */
public class ModeloTablas<T extends Objeto> implements FuncionesModelo, ModeloTablasCN {

    private final ArrayList<T> lista;
    private final DefaultTableModel modeloTabla;

    public ModeloTablas(ArrayList<T> lista, DefaultTableModel modeloTabla) {
        this.lista = lista;
        this.modeloTabla = modeloTabla;
    }

    @Override
    public void llenar() {
        for (T t : lista) {
            modeloTabla.addRow(t.getInfo());
        }
    }

    @Override
    public void vaciar() {
        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }
    }

    @Override
    public void recargar() {
        llenar();
        vaciar();
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    @Override
    public DefaultTableModel getModelo() {
        return modeloTabla;
    }

}

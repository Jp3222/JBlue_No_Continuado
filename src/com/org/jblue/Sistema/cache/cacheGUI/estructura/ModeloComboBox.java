/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.estructura;

import com.org.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author jp
 * @param <T>
 */
public class ModeloComboBox<T extends Objeto> implements FuncionesModelo, ModeloComboBoxCN {

    private final ArrayList<T> lista;
    private JComboBox<String> box;

    public ModeloComboBox(ArrayList<T> lista) {
        this.lista = lista;
    }

    @Override
    public void llenar() {
        for (T t : lista) {
            String[] info = t.getInfo();
            box.addItem(info[1]);
        }
    }

    @Override
    public void vaciar() {
        box.removeAllItems();
    }

    @Override
    public void recargar() {
        vaciar();
        llenar();
    }

    @Override
    public void setComboBox(JComboBox<String> o) {
        this.box = o;
    }

    @Override
    public JComboBox<String> getComboBox() {
        return box;
    }

    public ArrayList<T> getLista() {
        return lista;
    }

}

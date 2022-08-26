/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas;

import com.org.jblue.Sistema.cache.cacheBD.Cache;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.vistas.class_aux.Comparador;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class CacheTablas {

    private static CacheTablas instancia;

    public static CacheTablas getInstancia(DefaultTableModel titulares, DefaultTableModel Consumidores, DefaultTableModel tomas, DefaultTableModel calles) {
        if (instancia == null) {
            instancia = new CacheTablas(titulares, Consumidores, tomas, calles);
        }
        return instancia;
    }

    private final Cache cache;

    private final DefaultTableModel titulares;
    private final DefaultTableModel Consumidores;
    private final DefaultTableModel tomas;
    private final DefaultTableModel calles;
    private ArrayList<OTitulares> listaTitulares;
    private ArrayList<OConsumidores> listaConsumidores;
    private ArrayList<OTomas> listaTomas;
    private ArrayList<OCalles> listaCalles;

    public CacheTablas(DefaultTableModel titulares, DefaultTableModel Consumidores, DefaultTableModel tomas, DefaultTableModel calles) {
        this.cache = Cache.getInstancia();
        this.titulares = titulares;
        this.Consumidores = Consumidores;
        this.tomas = tomas;
        this.calles = calles;
        listaTitulares = cache.getTitulares();
        listaTomas = cache.getTomas();
        listaCalles = cache.getCalles();
    }

    //Tablas
    public void llenarTablaCalles() {
        ArrayList<OCalles> listadoDeCalles = cache.getCalles();
        for (OCalles calle : listadoDeCalles) {
            this.calles.addRow(calle.getInfo());
        }
    }

    public void vaciarTablaCalles() {
        while (calles.getRowCount() > 0) {
            calles.removeRow(0);
        }
    }

    public void recargarTablaCalles() {
        vaciarTablaCalles();
        llenarTablaCalles();
    }

    public void llenarTablaTomas() {
        ArrayList<OTomas> listadoDeTomas = cache.getTomas();
        for (OTomas toma : listadoDeTomas) {
            System.out.println("toma");
            this.tomas.addRow(toma.getInfo());
        }
    }

    public void vaciarTablaTomas() {
        while (tomas.getRowCount() > 0) {
            tomas.removeRow(0);
        }
    }

    public void recargarTablaTomas() {
        vaciarTablaTomas();
        llenarTablaTomas();
    }

    public void llenarTablaUsuarios() {
        ArrayList<OTitulares> usuarios = cache.getTitulares();
        int i = 0;
        for (OTitulares o : usuarios) {
            titulares.addRow(o.getInfo());
            OCalles calle = getBuscar(listaCalles, o.getCalle(), (x, y) -> x.getId().compareTo(y));
            titulares.setValueAt(calle.getNombre() + " " + calle.getNumero(), i, 4);
            OTomas toma = getBuscar(listaTomas, o.getToma(), (x, y) -> x.getId().compareTo(y));
            titulares.setValueAt(toma.getTipo(), i, 5);
        }

    }

    public <T, K> T getBuscar(ArrayList<T> lista, K buscado, Comparador<T, K> fun) {
        int izq = 0;
        int der = lista.size() - 1;
        int centro;
        while (izq <= der) {
            centro = (izq + der) / 2;
            T obj = lista.get(centro);
            int mov = fun.comparador(obj, buscado);
            switch (mov) {
                case 0:
                    return obj;
                case -1:
                    der = centro - 1;
                    break;
                case 1:
                    izq = centro + 1;
                    break;
                default:
                    break;
            }
        }
        return null;
    }

    public void vaciarTablaTitulares() {
        while (titulares.getRowCount() > 0) {
            titulares.removeRow(0);
        }
    }

    public void recargarTablaTitulares() {
        vaciarTablaTitulares();
        llenarTablaUsuarios();
    }

}

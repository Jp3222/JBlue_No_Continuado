/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesCalles extends Funciones implements Operaciones {

    public OperacionesCalles() {
        super("calle", Const.BD_CALLES);
    }

    @Override
    public boolean insertar(String[] valores) {
        return super.insertar(tabla, campos, valores);
    }

    @Override
    public boolean eliminar(String where) {
        return super.eliminar(tabla, where);
    }

    @Override
    public boolean actualizar(String campo, String valor, String where) {
        return super.actualizar(tabla, campo, valor, where);
    }

    @Override
    public boolean actualizar(String[] campos, String[] valores, String where) {
        return super.actualizar(tabla, campos, valores, where);
    }

    @Override
    public OCalles get(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            if (get.next()) {
                String[] o = new String[campos.length];
                OCalles p = new OCalles(o);
                return p;
            }
        } catch (SQLException ex) {
            System.out.println("operaciones calles");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<OCalles> getLista(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            ArrayList<OCalles> lista = new ArrayList<>(5);
            int i;
            int size = campos.length;
            while (get.next()) {
                i = 0;
                String[] o = new String[size];
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OCalles p = new OCalles(o);
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("operaciones calles");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<OCalles> getListaALL() {
        try {
            ResultSet get = cn.select(tabla);
            ArrayList<OCalles> lista = new ArrayList<>(5);
            int i;
            int size = campos.length;
            while (get.next()) {
                i = 0;
                String[] o = new String[size];
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OCalles p = new OCalles(o);
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("operaciones calles");
            System.out.println(ex.getMessage());
        }
        return null;
    }

}

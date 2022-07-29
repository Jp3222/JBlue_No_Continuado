/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesTomas extends Funciones implements Operaciones {

    public OperacionesTomas() {
        super("toma", Const.BD_TOMAS);
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
    public OTomas get(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            if (get.next()) {
                String[] o = new String[campos.length];
                int i = 0;
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OTomas p = new OTomas(o);
                return p;
            }
        } catch (SQLException ex) {
            System.out.println("operaciones tomas");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<OTomas> getLista(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            ArrayList<OTomas> lista = new ArrayList<>(5);
            int i;
            int size = campos.length;
            while (get.next()) {
                i = 0;
                String[] o = new String[size];
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OTomas p = new OTomas(o);
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("operaciones tomas");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String[] getCampos() {
        return campos;
    }

}

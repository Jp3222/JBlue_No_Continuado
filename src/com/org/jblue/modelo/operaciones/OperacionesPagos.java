/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.modelo.objetos.OPagos;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesPagos extends Funciones implements Operaciones {

    public OperacionesPagos() {
        super("pagos_hechos", new String[]{
            "id", "usuario", "mes_p", "dia", "mes", "anio"
        });
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
    public OPagos get(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            if (get.next()) {
                String[] o = new String[campos.length];
                int i = 0;
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OPagos p = new OPagos(o);
                return p;
            }
        } catch (SQLException ex) {
            System.out.println("operaciones personal");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<OPagos> getLista(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            ArrayList<OPagos> lista = new ArrayList<>(5);
            int i;
            int size = campos.length;
            while (get.next()) {
                i = 0;
                String[] array = new String[size];
                for (String campo : campos) {
                    array[i] = get.getString(campo);
                    i++;
                }
                OPagos o = new OPagos(array);
                lista.add(o);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("operaciones" + tabla);
            System.out.println(ex.getMessage());
        }
        return null;
    }

}

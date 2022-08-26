/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.Objeto;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesCalles extends Funciones implements Operaciones {

    public OperacionesCalles() {
        super("calles", Const.BD_CALLES);
    }

    @Override
    public boolean insertar(String[] valores) {
        return super.INSERTAR(valores);
    }

    @Override
    public boolean eliminar(String where) {
        return super.ELIMINAR(where);
    }

    @Override
    public boolean actualizar(String campo, String valor, String where) {
        return super.ACTUALIZAR(campo, valor, where);
    }

    @Override
    public boolean actualizar(String[] campos, String[] valores, String where) {
        return super.ACTUALIZAR(campos, valores, where);
    }

    @Override
    public OCalles get(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        OCalles o = (OCalles) get.get(0);
        get.clear();
        return o;
    }

    @Override
    public ArrayList<OCalles> getLista(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        ArrayList<OCalles> lista = new ArrayList<>(get.size());
        for (Objeto objeto : get) {
            lista.add((OCalles) objeto);
        }
        get.clear();
        return lista;
    }

}

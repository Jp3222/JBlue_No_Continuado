/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.objetos.Objeto;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesTomas extends Funciones implements Operaciones {

    public OperacionesTomas() {
        super("tomas", Const.BD_TOMAS);
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
    public OTomas get(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        OTomas o = (OTomas) get.get(0);
        return o;
    }

    @Override
    public ArrayList<OTomas> getLista(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        ArrayList<OTomas> lista = new ArrayList<>(get.size());
        for (Objeto objeto : get) {
            lista.add((OTomas) objeto);
        }
        get.clear();
        return lista;
    }

}

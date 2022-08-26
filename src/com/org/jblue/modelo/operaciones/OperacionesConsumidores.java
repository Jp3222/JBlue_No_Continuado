/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.Objeto;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesConsumidores extends Funciones implements Operaciones {

    public OperacionesConsumidores() {
        super("consumidores", Const.BD_CONSUMIDORES);
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
    public OConsumidores get(String where) {
        ArrayList<Objeto> get = GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        OConsumidores o = (OConsumidores) get.get(0);
        get.clear();
        return o;
    }

    @Override
    public ArrayList<OConsumidores> getLista(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        ArrayList<OConsumidores> lista = new ArrayList<>(get.size());
        for (Objeto objeto : get) {
            lista.add((OConsumidores) objeto);
        }
        get.clear();
        return lista;
    }

}

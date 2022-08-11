/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.Objeto;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesTitulares extends Funciones implements Operaciones {

    public OperacionesTitulares() {
        super("usuario", Const.BD_TITULARES);
    }

    @Override
    public boolean insertar(String[] valores) {
        return super.INSERTAR( valores);
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
    public OTitulares get(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get.isEmpty()) {
            return null;
        }
        return (OTitulares) get.get(0);
    }

    @Override
    public ArrayList<OTitulares> getLista(String where) {
        ArrayList<Objeto> get = super.GET( where);
        if (get.isEmpty()) {
            return null;
        }
        ArrayList<OTitulares> lista = new ArrayList<>(get.size());
        for (Objeto objeto : get) {
            lista.add((OTitulares) objeto);
        }
        return lista;
    }

}

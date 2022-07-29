/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.Objeto;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesUsuarios extends Funciones implements Operaciones {

    public OperacionesUsuarios() {
        super("usuario", Const.BD_USUARIOS);
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
    public <T extends Objeto> T get(String where) {
        return null;
    }

    @Override
    public <T extends Objeto> ArrayList<T> getLista(String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

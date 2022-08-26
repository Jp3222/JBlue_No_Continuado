/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Const;
import com.org.jblue.Sistema.funciones.EncriptadorAES;
import com.org.jblue.modelo.objetos.OPersonal;
import com.org.jblue.modelo.objetos.Objeto;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesPersonal extends Funciones implements Operaciones {

    private final int user;
    private final int pass;
    private final EncriptadorAES encriptador;

    public OperacionesPersonal() {
        super("personal", Const.BD_PERSONAL);
        encriptador = EncriptadorAES.getInstancia();
        user = campos.length - 2;
        pass = campos.length - 1;
    }

    @Override
    public boolean insertar(String[] valores) {
        String usuario = valores[user];
        String password = valores[pass];
        System.out.println(usuario);
        System.out.println(password);
        valores[user] = encriptador.encriptar(usuario, password);
        valores[pass] = encriptador.encriptar(password, usuario);
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
    public OPersonal get(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        OPersonal o = (OPersonal) get.get(0);
        get.clear();
        return o;
    }

    @Override
    public ArrayList<OPersonal> getLista(String where) {
        ArrayList<Objeto> get = super.GET(where);
        if (get == null || get.isEmpty()) {
            return null;
        }
        ArrayList<OPersonal> lista = new ArrayList<>(get.size());
        for (Objeto objeto : get) {
            lista.add((OPersonal) objeto);
        }
        get.clear();
        return lista;
    }

}

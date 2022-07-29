/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones;

import com.org.jblue.Sistema.funciones.EncriptadorAES;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OPersonal;
import com.org.jblue.modelo.operaciones.aux.Funciones;
import com.org.jblue.modelo.operaciones.aux.Operaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OperacionesPersonal extends Funciones implements Operaciones {

    private int user;
    private int pass;
    private EncriptadorAES encriptador;

    public OperacionesPersonal() {
        super("personal", new String[]{
            "id", "nombre", "apellidos", "cargo", "usuario", "contra"
        });
        encriptador = EncriptadorAES.getInstancia();
        user = campos.length - 2;
        pass = campos.length - 1;
    }

    @Override
    public boolean insertar(String[] valores) {
        String usuario = valores[user];
        String password = valores[pass];
        valores[user] = encriptador.encriptar(usuario, password);
        valores[pass] = encriptador.encriptar(password, usuario);
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
    public OPersonal get(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            if (get.next()) {
                String[] o = new String[campos.length];
                int i = 0;
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OPersonal p = new OPersonal(o);
                return p;
            }
        } catch (SQLException ex) {
            System.out.println("operaciones personal");
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<OPersonal> getLista(String where) {
        try {
            ResultSet get = super.get(tabla, "*", where);
            ArrayList<OPersonal> lista = new ArrayList<>(5);
            int i;
            int size = campos.length;
            while (get.next()) {
                i = 0;
                String[] o = new String[size];
                for (String campo : campos) {
                    o[i] = get.getString(campo);
                    i++;
                }
                OPersonal O = new OPersonal(o);
                lista.add(O);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("operaciones personal");
            System.out.println(ex.getMessage());
        }
        return null;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema;

import com.jsql.conexion.Conexion;
import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.objetos.OUsuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class Cache {

    private static final Cache instancia = new Cache();

    public static Cache getInstancia() {
        return instancia;
    }

    private final ArrayList<OTomas> tomas;
    private final ArrayList<OCalles> calles;
    private final ArrayList<OUsuarios> usuarios;

    private final Conexion cn;

    private Cache() {
        cn = Conexion.getInstancia();
        tomas = new ArrayList<>();
        calles = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void init() {
        reCargarTomas();
        reCargarCalles();
        reCargarUsuarios();
    }

    public void cargarTomas() {
        try {
            System.out.println("xd 1");
            ResultSet select = cn.select("toma");
            int i;
            while (select.next()) {
                String[] array = new String[Const.BD_TOMAS.length];
                i = 0;
                for (String col : Const.BD_TOMAS) {
                    array[i] = select.getString(col);
                    i++;
                }
                OTomas o = new OTomas(array);
                tomas.add(o);
            }
            cn.cerrarStament();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reCargarTomas() {
        while (!tomas.isEmpty()) {
            tomas.remove(0);
        }
        cargarTomas();
    }

    public void cargarCalles() {
        try {
            ResultSet select = cn.select("calle");
            while (select.next()) {
                String[] array = new String[Const.BD_CALLES.length];
                for (int i = 0; i < array.length; i++) {
                    array[i] = select.getString(i + 1);
                }
                OCalles o = new OCalles(array);
                calles.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reCargarCalles() {
        while (!calles.isEmpty()) {
            calles.remove(0);
        }
        cargarCalles();
    }

    public void cargarUsuarios() {
        try {
            ResultSet select = cn.select("usuario");
            while (select.next()) {
                String[] array = new String[Const.BD_USUARIOS.length];
                for (int i = 0; i < array.length; i++) {
                    array[i] = select.getString(i + 1);
                }
                OUsuarios o = new OUsuarios(array);
                usuarios.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reCargarUsuarios() {
        while (!usuarios.isEmpty()) {
            usuarios.remove(0);
        }
        cargarUsuarios();
    }

    public ArrayList<OTomas> getTomas() {
        return tomas;
    }

    public ArrayList<OCalles> getCalles() {
        return calles;
    }

    public ArrayList<OUsuarios> getUsuarios() {
        return usuarios;
    }

}

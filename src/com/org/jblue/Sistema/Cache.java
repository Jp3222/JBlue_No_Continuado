/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema;

import com.jsql.conexion.Conexion;
import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.objetos.OTitulares;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase encargada de almacenar datos de la base de datos en la memoria RAM del
 * ordenador con el proposito de tener un acceso instantaneo
 *
 * @author jp
 */
public class Cache {

    /**
     * Variable unica de instancia de la clase "Cache"
     */
    private static final Cache instancia = new Cache();

    /**
     * Metodo de acceso a la instancia de la clase Cache
     *
     * @return una instancia de tipo Cache
     */
    public synchronized static Cache getInstancia() {
        return instancia;
    }

    /**
     * Lista encargada a almacenar objetos de tipo tomas que son leidos de la
     * base de datos
     */
    private final ArrayList<OTomas> tomas;
    /**
     * Lista encargada a almacenar objetos de tipo calles que son leidos de la
     * base de datos
     */
    private final ArrayList<OCalles> calles;
    /**
     * Lista encargada a almacenar objetos de tipo usuarios que son leidos de la
     * base de datos
     */
    private final ArrayList<OTitulares> usuarios;
    /**
     * Variable en Conexion a la base de datos
     */
    private final Conexion cn;

    /**
     * Constructor de la clase Cache. 
     * <br>Instancia 3 objetos del tipo "ArrayList" con
 los datos de objetos "OTomas","OCalles","OTitulares",
     */
    private Cache() {
        cn = Conexion.getInstancia();
        tomas = new ArrayList<>();
        calles = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void init() {
        //reCargarTomas();
        //reCargarCalles();
        //reCargarUsuarios();
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
                String[] array = new String[Const.BD_TITULARES.length];
                for (int i = 0; i < array.length; i++) {
                    array[i] = select.getString(i + 1);
                }
                OTitulares o = new OTitulares(array);
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

    public ArrayList<OTitulares> getUsuarios() {
        return usuarios;
    }

}

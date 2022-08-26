/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheBD;

import com.jsql.conexion.Conexion;
import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.OTomas;
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
     * Lista encargada a almacenar objetos de tipo titulares que son leidos de
     * la base de datos
     */
    private final ArrayList<OTitulares> titulares;

    /**
     * Lista encargada a almacenar objetos de tipo OConsumidores que son leidos
     * de la base de datos
     */
    private final ArrayList<OConsumidores> consumidores;
    /**
     * Variable de conexion a la base de datos
     */
    private final Conexion cn;

    /**
     * Constructor de la clase Cache.
     * <br>Instancia 3 objetos del tipo "ArrayList" con los datos de objetos
     * "OTomas","OCalles","OTitulares",
     */
    private Cache() {
        this.consumidores = new ArrayList<>();
        cn = Conexion.getInstancia();
        tomas = new ArrayList<>();
        calles = new ArrayList<>();
        titulares = new ArrayList<>();
    }

    public void init() {
        cargarTomas();
        cargarCalles();
        cargarTitulares();
    }

    public void cargarTomas() {
        try {
            ResultSet select = cn.select("tomas");
            String[] campos = Const.BD_TOMAS;
            int tamanio = campos.length;
            while (select.next()) {
                String[] array = getArray(select, tamanio, campos);
                OTomas o = new OTomas(array);
                tomas.add(o);
            }
            cn.cerrarStament();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void vaciarTomas() {
        tomas.clear();
    }

    public void reCargarTomas() {
        vaciarTomas();
        cargarTomas();
    }

    public void cargarCalles() {
        try {
            try ( ResultSet select = cn.select("calles")) {
                String campos[] = Const.BD_CALLES;
                int tamanio = campos.length;
                while (select.next()) {
                    String[] array = getArray(select, tamanio, campos);
                    OCalles o = new OCalles(array);
                    calles.add(o);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void vaciarCalles() {
        calles.clear();
    }

    public void reCargarCalles() {
        vaciarCalles();
        cargarCalles();
    }

    public void cargarTitulares() {
        try {
            try ( ResultSet select = cn.select("titulares")) {
                String[] campos = Const.BD_TITULARES;
                int tamanio = campos.length;
                while (select.next()) {
                    String[] array = getArray(select, tamanio, campos);
                    OTitulares o = new OTitulares(array);
                    titulares.add(o);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void vaciarTitulares() {
        titulares.clear();
    }

    public void reCargarTitulares() {
        vaciarTitulares();
        cargarTitulares();
    }

    private String[] getArray(ResultSet rs, int tamanio, String[] campos) throws SQLException {
        String[] array = new String[tamanio];
        int i = 0;
        for (String campo : campos) {
            array[i] = rs.getString(campo);
            i++;
        }
        return array;
    }

    public ArrayList<OTomas> getTomas() {
        return tomas;
    }

    public ArrayList<OCalles> getCalles() {
        return calles;
    }

    public ArrayList<OTitulares> getTitulares() {
        return titulares;
    }

    public ArrayList<OConsumidores> getConsumidores() {
        return consumidores;
    }

    
}

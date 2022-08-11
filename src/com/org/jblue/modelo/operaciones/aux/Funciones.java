/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones.aux;

import com.jsql.conexion.Conexion;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OPersonal;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.objetos.Objeto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jp
 */
public class Funciones {

    protected final Conexion cn;
    protected final String tabla;
    protected final String[] campos;
    protected final int noCampos;

    public Funciones(String tabla, String[] campos) {
        this.cn = Conexion.getInstancia();
        this.tabla = tabla;
        this.campos = campos;
        this.noCampos = campos.length;
    }

    protected boolean INSERTAR(String[] valores) {
        return cn.insert(tabla,
                cn.getCampos(Arrays.copyOfRange(campos, 1, campos.length)),
                cn.getDatos(Arrays.copyOfRange(valores, 1, valores.length))
        );
    }

    protected boolean ELIMINAR(String where) {
        return cn.delete(tabla, where);
    }

    protected boolean ACTUALIZAR(String campo, String valor, String where) {
        return cn.update(tabla, campo, valor, where);
    }

    protected boolean ACTUALIZAR(String campos[], String valores[], String where) {
        return cn.update(tabla, cn.getCampos_Datos(campos, valores), where);
    }

    protected ArrayList<Objeto> GET(String where) {
        if (where == null || where.isEmpty()) {
            throw new NullPointerException("error en la clase funciones llamada por la clase operaciones " + tabla);
        }
        ResultSet get = cn.select(tabla, "*", where);
        if (get == null) {
            throw new NullPointerException("error en la clase funciones llamada por la clase operaciones " + tabla);
        }
        ArrayList<Objeto> lista = null;
        try {
            lista = getLista(get, noCampos, tabla, campos);
            get.close();
            cn.cerrarStament();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return lista;
    }

    private ArrayList<Objeto> getLista(ResultSet get, int size, String tabla, String[] campos) throws SQLException {
        if (get == null) {
            System.out.println("null");
            return null;
        } else {
            System.out.println("oks");
        }
        final ArrayList<Objeto> lista = new ArrayList<>();
        int i;
        switch (tabla) {
            case "calles":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OCalles p = new OCalles(info);
                    lista.add(p);
                }
                break;
            case "consumidores":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OTomas p = new OTomas(info);
                    lista.add(p);
                }
                break;
            case "pagos_consumidores":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OPersonal p = new OPersonal(info);
                    lista.add(p);
                }
                break;
            case "pagos_titulares":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OPersonal p = new OPersonal(info);
                    lista.add(p);
                }
                break;
            case "personal":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OPersonal p = new OPersonal(info);
                    lista.add(p);
                }
                break;
            case "titulares":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OPersonal p = new OPersonal(info);
                    lista.add(p);
                }
                break;
            case "tomas":
                while (get.next()) {
                    String[] info = getArrayInfo(get, campos, size);
                    OTomas p = new OTomas(info);
                    lista.add(p);
                }
                break;
        }
        return lista;
    }

    private <T extends Objeto> ArrayList<T> runWhile(T o, ResultSet get, int size, String[] campos) throws SQLException, CloneNotSupportedException {
        ArrayList<T> lista = new ArrayList<>();
        while (get.next()) {
            String[] info = getArrayInfo(get, campos, size);
            o.setInfo(info);
            lista.add((T) o.clone());
        }
        return lista;
    }

    /**
     * Este metodo que recupera informacion de la base de datos segun los campos
     * especificados.
     *
     * Este metodo no cierra el objeto de tipo ResultSet puesto que se espera
     * que se utilice en bluces
     *
     * @param get objeto de tipos ResultSet sobre el cual se esta trabajando
     * @param campos Array de String que contienen los campos sobre los cuales
     * se va a recuperar informacion
     * @param size tamaño del array con el cual se esta trabajando
     * @return un Array con los datos recuperados o una exepcion del tipo
     * nullPointerException o del tipo SQLException
     * @throws SQLException
     */
    public String[] getArrayInfo(ResultSet get, String[] campos, int size) throws SQLException {
        if (get == null) {
            throw new NullPointerException("Error en el metodo getArrayInfo");
        }
        if (campos == null) {
            throw new NullPointerException("Error en el metodo getArrayInfo");
        }
        String[] info = new String[size];
        int i = 0;
        for (String campo : campos) {
            info[i] = get.getString(campo);
            i++;
        }
        return info;
    }

    public String getTabla() {
        return tabla;
    }

    public String[] getCampos() {
        return campos;
    }

    public Conexion getCn() {
        return cn;
    }

}
